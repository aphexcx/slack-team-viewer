
package cx.aphex.slackteamviewer.models;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import fj.data.Option;

//@Generated("org.jsonschema2pojo")
public class Profile {
    private String first_name;
    private String last_name;
    private String image_24;
    private String image_32;
    private String image_48;
    private String image_72;
    private String image_192;
    private String image_512;
    private String image_original;
    private String avatar_hash;
    private String real_name;
    private String real_name_normalized;
    private @NonNull Option<String> phone = Option.none();
    // For email, sometimes the option itself can be null, because moshi blithely
    // shoves null in here if the field is explicitly null in the JSON.
    // Slackbot, I'm looking at you! Again! *grrr*
    private @Nullable Option<String> email = Option.none();
    private @NonNull Option<String> title = Option.none();
    private Object fields;

    public Profile() {
    }

    /**
     * @return The first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param first_name The first_name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return The last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name The last_name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return The image_24
     */
    public String getImage_24() {
        return image_24;
    }

    /**
     * @param image_24 The image_24
     */
    public void setImage_24(String image_24) {
        this.image_24 = image_24;
    }

    /**
     * @return The image_32
     */
    public String getImage_32() {
        return image_32;
    }

    /**
     * @param image_32 The image_32
     */
    public void setImage_32(String image_32) {
        this.image_32 = image_32;
    }

    /**
     * @return The image_48
     */
    public String getImage_48() {
        return image_48;
    }

    /**
     * @param image_48 The image_48
     */
    public void setImage_48(String image_48) {
        this.image_48 = image_48;
    }

    /**
     * @return The image_72
     */
    public String getImage_72() {
        return image_72;
    }

    /**
     * @param image_72 The image_72
     */
    public void setImage_72(String image_72) {
        this.image_72 = image_72;
    }

    /**
     * @return The image_192
     */
    public String getImage_192() {
        return image_192;
    }

    /**
     * @param image_192 The image_192
     */
    public void setImage_192(String image_192) {
        this.image_192 = image_192;
    }

    /**
     * @return The image_512
     */
    public String getImage_512() {
        return image_512;
    }

    /**
     * @param image_512 The image_512
     */
    public void setImage_512(String image_512) {
        this.image_512 = image_512;
    }

    /**
     * @return The image_original
     */
    public String getImage_original() {
        return image_original;
    }

    /**
     * @param image_original The image_original
     */
    public void setImage_original(String image_original) {
        this.image_original = image_original;
    }

    /**
     * @return The avatar_hash
     */
    public String getAvatar_hash() {
        return avatar_hash;
    }

    /**
     * @param avatar_hash The avatar_hash
     */
    public void setAvatar_hash(String avatar_hash) {
        this.avatar_hash = avatar_hash;
    }

    /**
     * @return The real_name
     */
    public String getReal_name() {
        return real_name;
    }

    /**
     * @param real_name The real_name
     */
    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    /**
     * @return The real_name_normalized
     */
    public String getReal_name_normalized() {
        return real_name_normalized;
    }

    /**
     * @param real_name_normalized The real_name_normalized
     */
    public void setReal_name_normalized(String real_name_normalized) {
        this.real_name_normalized = real_name_normalized;
    }

    /**
     * @return The email
     */
    public Option<String> getEmail() {
        // The option itself can be null, so here's another Option null wrapper
        // followed by a flatten.
        return Option.join(Option.fromNull(email));
    }

    /**
     * @param email The email
     */
    public void setEmail(String email) {
        this.email = Option.some(email);
    }

    /**
     * @return The phone
     */
    public Option<String> getPhone() {
        return phone;
    }

    /**
     * @param phone The phone
     */
    public void setPhone(String phone) {
        this.phone = Option.some(phone);
    }

    /**
     * @return The fields
     */
    public Object getFields() {
        return fields;
    }

    /**
     * @param fields The fields
     */
    public void setFields(Object fields) {
        this.fields = fields;
    }

    /**
     * @return The title
     */
    public Option<String> getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = Option.some(title);
    }
}
