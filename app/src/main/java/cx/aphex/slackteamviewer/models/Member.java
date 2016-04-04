
package cx.aphex.slackteamviewer.models;


import android.support.annotation.ColorInt;

//@Generated("org.jsonschema2pojo")
public class Member {

    private String id;
    private String team_id;
    private String name;
    private boolean deleted;
    private Object status;
    private @HexColor @ColorInt int color;
    private String real_name;
    private Object tz;
    private String tz_label;
    private int tz_offset;
    private Profile profile;
    private boolean is_admin;
    private boolean is_owner;
    private boolean is_primary_owner;
    private boolean is_restricted;
    private boolean is_ultra_restricted;
    private boolean is_bot;
    private boolean has_2fa;
    private String presence;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The team_id
     */
    public String getTeam_id() {
        return team_id;
    }

    /**
     * @param team_id The team_id
     */
    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted The deleted
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return The status
     */
    public Object getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(Object status) {
        this.status = status;
    }

    /**
     * @return The color
     */
    public
    @ColorInt
    int getColor() {
        return color;
    }

    /**
     * @param color The color
     */
    public void setColor(@ColorInt int color) {
        this.color = color;
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
     * @return The tz
     */
    public Object getTz() {
        return tz;
    }

    /**
     * @param tz The tz
     */
    public void setTz(Object tz) {
        this.tz = tz;
    }

    /**
     * @return The tz_label
     */
    public String getTz_label() {
        return tz_label;
    }

    /**
     * @param tz_label The tz_label
     */
    public void setTz_label(String tz_label) {
        this.tz_label = tz_label;
    }

    /**
     * @return The tz_offset
     */
    public int getTz_offset() {
        return tz_offset;
    }

    /**
     * @param tz_offset The tz_offset
     */
    public void setTz_offset(int tz_offset) {
        this.tz_offset = tz_offset;
    }

    /**
     * @return The profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * @param profile The profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * @return The is_admin
     */
    public boolean isIs_admin() {
        return is_admin;
    }

    /**
     * @param is_admin The is_admin
     */
    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    /**
     * @return The is_owner
     */
    public boolean isIs_owner() {
        return is_owner;
    }

    /**
     * @param is_owner The is_owner
     */
    public void setIs_owner(boolean is_owner) {
        this.is_owner = is_owner;
    }

    /**
     * @return The is_primary_owner
     */
    public boolean isIs_primary_owner() {
        return is_primary_owner;
    }

    /**
     * @param is_primary_owner The is_primary_owner
     */
    public void setIs_primary_owner(boolean is_primary_owner) {
        this.is_primary_owner = is_primary_owner;
    }

    /**
     * @return The is_restricted
     */
    public boolean isIs_restricted() {
        return is_restricted;
    }

    /**
     * @param is_restricted The is_restricted
     */
    public void setIs_restricted(boolean is_restricted) {
        this.is_restricted = is_restricted;
    }

    /**
     * @return The is_ultra_restricted
     */
    public boolean isIs_ultra_restricted() {
        return is_ultra_restricted;
    }

    /**
     * @param is_ultra_restricted The is_ultra_restricted
     */
    public void setIs_ultra_restricted(boolean is_ultra_restricted) {
        this.is_ultra_restricted = is_ultra_restricted;
    }

    /**
     * @return The is_bot
     */
    public boolean isIs_bot() {
        return is_bot;
    }

    /**
     * @param is_bot The is_bot
     */
    public void setIs_bot(boolean is_bot) {
        this.is_bot = is_bot;
    }

    /**
     * @return The has_2fa
     */
    public boolean isHas_2fa() {
        return has_2fa;
    }

    /**
     * @param has_2fa The has_2fa
     */
    public void setHas_2fa(boolean has_2fa) {
        this.has_2fa = has_2fa;
    }

    /**
     * @return The presence
     */
    public String getPresence() {
        return presence;
    }

    /**
     * @param presence The presence
     */
    public void setPresence(String presence) {
        this.presence = presence;
    }

}
