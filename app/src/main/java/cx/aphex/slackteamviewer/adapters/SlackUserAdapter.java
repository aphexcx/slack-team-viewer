package cx.aphex.slackteamviewer.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;
import cx.aphex.slackteamviewer.R;
import cx.aphex.slackteamviewer.models.Member;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by aphex on 4/3/16.
 */
public class SlackUserAdapter extends RecyclerView.Adapter<SlackUserAdapter.ViewHolder> {
    public Subject<Member, Member> memberClicks = new SerializedSubject<>(PublishSubject.create());
    ArrayList<Member> mItems = new ArrayList<>();
    private Context mContext;
    private String TAG = this.getClass().getSimpleName();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        final Member member = mItems.get(position);

        vh.llItemRoot.setOnClickListener(v -> memberClicks.onNext(member));

        vh.userName.setText(member.getName());
        vh.llItemRoot.setBackgroundColor(member.getColor());
        vh.fullName.setText(member.getReal_name());

        vh.profileImage.setImageURI(Uri.parse(member.getProfile().getImage_192()));
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(Collection<Member> items) {
        Log.i(TAG, items.size() + " items received.");
        if (items.equals(mItems)) {
            Log.i(TAG, "New list is the same as the current list.");
            return;
        }
        clearItems();
        fj.data.List.list(items)
                .foreachDoEffect(this::addItem);
        Log.i(TAG, items.size() + " items added.");
    }

    public void addItem(Member item) {
        mItems.add(item);
        notifyItemInserted(mItems.size() - 1);
    }

    public void removeItem(Member item) {
        remove(mItems.indexOf(item));
    }

    public void remove(int index) {
        mItems.remove(index);
        notifyItemRemoved(index);
    }

    public void clearItems() {
        int oldSize = mItems.size();
        mItems.clear();
        notifyItemRangeRemoved(0, oldSize);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.llItemRoot)
        LinearLayout llItemRoot;
        @Bind(R.id.profileImage)
        SimpleDraweeView profileImage;
        @Bind(R.id.userName)
        TextView userName;
        @Bind(R.id.fullName)
        TextView fullName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}