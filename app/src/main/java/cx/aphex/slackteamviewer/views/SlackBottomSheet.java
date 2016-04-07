package cx.aphex.slackteamviewer.views;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cx.aphex.slackteamviewer.R;
import cx.aphex.slackteamviewer.models.Member;
import cx.aphex.slackteamviewer.models.Profile;

/**
 * Created by aphex on 4/7/16.
 */
public class SlackBottomSheet extends FrameLayout {

    @Bind(R.id.fullName) TextView fullName;
    @Bind(R.id.userName) TextView userName;
    @Bind(R.id.profileImage) SimpleDraweeView profileImage;
    @Bind(R.id.phoneNumber) TextView phoneNumber;
    @Bind(R.id.email) TextView email;

    public SlackBottomSheet(Context context) {
        super(context);
    }

    public SlackBottomSheet(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlackBottomSheet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SlackBottomSheet(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setMember(Member member) {
        setBackgroundColor(member.getColor());
        fullName.setText(member.getReal_name());
        userName.setText(member.getName());

        Profile profile = member.getProfile();
        phoneNumber.setText(profile.getPhone());
        email.setText(profile.getEmail());
        profileImage.setImageURI(Uri.parse(profile.getImage_512()));
    }
}