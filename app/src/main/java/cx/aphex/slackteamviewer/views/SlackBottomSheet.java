package cx.aphex.slackteamviewer.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    @Bind(R.id.phoneRow) LinearLayout phoneRow;
    @Bind(R.id.emailRow) LinearLayout emailRow;
    @Bind(R.id.workTitleRow) LinearLayout workTitleRow;
    @Bind(R.id.timeRow) LinearLayout timeRow;

    @NonNull
    private String memberTz = Calendar.getInstance().getTimeZone().getDisplayName();

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

    @SuppressWarnings("WrongConstant")
    public void setMember(Member member) {
        @ColorInt int memberColor = member.getColor();
        setBackgroundColor(memberColor);
        ButterKnife.apply(icons, (view, index) ->
                view.setColorFilter(memberColor));

        fullName.setText(member.getReal_name());
        userName.setText("@" + member.getName());

        Profile profile = member.getProfile();

        phoneRow.setVisibility(profile.getPhone().option(GONE, p -> VISIBLE));
        profile.getPhone().foreachDoEffect(phoneNumber::setText);

        emailRow.setVisibility(profile.getEmail().option(GONE, e -> VISIBLE));
        profile.getEmail().foreachDoEffect(email::setText);

        workTitleRow.setVisibility(profile.getEmail().option(GONE, w -> VISIBLE));
        profile.getTitle().foreachDoEffect(workTitle::setText);

        timeRow.setVisibility(member.getTz().option(GONE, t -> VISIBLE));
        member.getTz().foreachDoEffect(tz ->
                memberTz = tz);

        timeLabel.setText(profile.getFirst_name() + "'s local time (" + member.getTz_label() + ")");

        // Set a high quality image as the profile pic.
        // This is usually in image_original but can be in image_512
        // because the api is weird.
        Option.fromNull(profile.getImage_original())
                .orElse(Option.fromNull(profile.getImage_512()))
                .map(Uri::parse)
                .foreachDoEffect(profileImage::setImageURI);

        // Make the visibility changes, if any, render faster
        requestLayout();
    }

    private String calculateMemberTime(String tz) {
        DateFormat sdf = SimpleDateFormat.getTimeInstance();
        sdf.setTimeZone(TimeZone.getTimeZone(tz));
        return sdf.format(Calendar.getInstance().getTime());
    }
}