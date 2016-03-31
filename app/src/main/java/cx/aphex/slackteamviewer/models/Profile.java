package cx.aphex.slackteamviewer.models;

import java.util.HashMap;
import java.util.Map;

//@Generated("org.jsonschema2pojo")
public class Profile {

    private String firstName;
    private String lastName;
    private String image24;
    private String image32;
    private String image48;
    private String image72;
    private String image192;
    private String image512;
    private String avatarHash;
    private String realName;
    private String realNameNormalized;
    private Object email;
    private Object fields;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The first_name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName The last_name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The image24
     */
    public String getImage24() {
        return image24;
    }

    /**
     * @param image24 The image_24
     */
    public void setImage24(String image24) {
        this.image24 = image24;
    }

    /**
     * @return The image32
     */
    public String getImage32() {
        return image32;
    }

    /**
     * @param image32 The image_32
     */
    public void setImage32(String image32) {
        this.image32 = image32;
    }

    /**
     * @return The image48
     */
    public String getImage48() {
        return image48;
    }

    /**
     * @param image48 The image_48
     */
    public void setImage48(String image48) {
        this.image48 = image48;
    }

    /**
     * @return The image72
     */
    public String getImage72() {
        return image72;
    }

    /**
     * @param image72 The image_72
     */
    public void setImage72(String image72) {
        this.image72 = image72;
    }

    /**
     * @return The image192
     */
    public String getImage192() {
        return image192;
    }

    /**
     * @param image192 The image_192
     */
    public void setImage192(String image192) {
        this.image192 = image192;
    }

    /**
     * @return The image512
     */
    public String getImage512() {
        return image512;
    }

    /**
     * @param image512 The image_512
     */
    public void setImage512(String image512) {
        this.image512 = image512;
    }

    /**
     * @return The avatarHash
     */
    public String getAvatarHash() {
        return avatarHash;
    }

    /**
     * @param avatarHash The avatar_hash
     */
    public void setAvatarHash(String avatarHash) {
        this.avatarHash = avatarHash;
    }

    /**
     * @return The realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName The real_name
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return The realNameNormalized
     */
    public String getRealNameNormalized() {
        return realNameNormalized;
    }

    /**
     * @param realNameNormalized The real_name_normalized
     */
    public void setRealNameNormalized(String realNameNormalized) {
        this.realNameNormalized = realNameNormalized;
    }

    /**
     * @return The email
     */
    public Object getEmail() {
        return email;
    }

    /**
     * @param email The email
     */
    public void setEmail(Object email) {
        this.email = email;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
