package cx.aphex.slackteamviewer.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import cx.aphex.slackteamviewer.R;
import cx.aphex.slackteamviewer.models.Member;
import cx.aphex.slackteamviewer.models.Profile;
import fj.data.Option;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by aphex on 4/7/16.
 */
public class SlackBottomSheet extends FrameLayout {

    @Bind(R.id.fullName) TextView fullName;
    @Bind(R.id.userName) TextView userName;
    @Bind(R.id.profileImage) SimpleDraweeView profileImage;
    @Bind(R.id.phoneNumber) TextView phoneNumber;
    @Bind(R.id.email) TextView email;
    @Bind(R.id.workTitle) TextView workTitle;
    @Bind(R.id.currentTime) TextView currentTime;
    @Bind(R.id.timeLabel) TextView timeLabel;
    @Bind({R.id.phoneIcon, R.id.emailIcon, R.id.workTitleIcon, R.id.timeIcon})
    List<ImageView> icons;

    @NonNull private String memberTz = Calendar.getInstance().getTimeZone().getDisplayName();

    private Observable<String> clockObservable =
            Observable.interval(500, TimeUnit.MILLISECONDS)
                    .map(l -> calculateMemberTime(memberTz));

    public SlackBottomSheet(Context context) {
        super(context);
    }

    public SlackBottomSheet(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlackBottomSheet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public SlackBottomSheet(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);

        clockObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(currentTime::setText);
    }

    public void setMember(Member member) {
        @ColorInt int memberColor = member.getColor();
        setBackgroundColor(memberColor);
        ButterKnife.apply(icons, (view, index) ->
                view.setColorFilter(memberColor));

        fullName.setText(member.getReal_name());
        userName.setText("@" + member.getName());

        Profile profile = member.getProfile();
        phoneNumber.setText(profile.getPhone());
        email.setText(profile.getEmail());

        workTitle.setText(profile.getTitle());

        // Tz is null in slackbot's case. Default to our own timezone
        // This should really just be omitted for slackbot instead
        memberTz = Option.fromNull(member.getTz())
                .orSome(Calendar.getInstance()
                        .getTimeZone()
                        .getDisplayName());

        timeLabel.setText(profile.getFirst_name() + "'s local time (" + member.getTz_label() + ")");

        // Set a high quality image as the profile pic.
        // This is usually in image_original but can be in image_512
        // because the api is weird.
        Option.fromNull(profile.getImage_original())
                .orElse(Option.fromNull(profile.getImage_512()))
                .map(Uri::parse)
                .foreachDoEffect(profileImage::setImageURI);
    }

    private String calculateMemberTime(String tz) {
        DateFormat sdf = SimpleDateFormat.getTimeInstance();
        sdf.setTimeZone(TimeZone.getTimeZone(tz));
        return sdf.format(Calendar.getInstance().getTime());
    }
}