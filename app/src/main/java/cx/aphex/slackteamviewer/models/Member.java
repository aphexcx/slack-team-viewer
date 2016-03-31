package cx.aphex.slackteamviewer.models;

import java.util.HashMap;
import java.util.Map;

//@Generated("org.jsonschema2pojo")
public class Member {

    private String id;
    private String teamId;
    private String name;
    private boolean deleted;
    private Object status;
    private String color;
    private String realName;
    private Object tz;
    private String tzLabel;
    private int tzOffset;
    private Profile profile;
    private boolean isAdmin;
    private boolean isOwner;
    private boolean isPrimaryOwner;
    private boolean isRestricted;
    private boolean isUltraRestricted;
    private boolean isBot;
    private boolean has2fa;
    private String presence;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     * @return The teamId
     */
    public String getTeamId() {
        return teamId;
    }

    /**
     * @param teamId The team_id
     */
    public void setTeamId(String teamId) {
        this.teamId = teamId;
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
    public String getColor() {
        return color;
    }

    /**
     * @param color The color
     */
    public void setColor(String color) {
        this.color = color;
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
     * @return The tzLabel
     */
    public String getTzLabel() {
        return tzLabel;
    }

    /**
     * @param tzLabel The tz_label
     */
    public void setTzLabel(String tzLabel) {
        this.tzLabel = tzLabel;
    }

    /**
     * @return The tzOffset
     */
    public int getTzOffset() {
        return tzOffset;
    }

    /**
     * @param tzOffset The tz_offset
     */
    public void setTzOffset(int tzOffset) {
        this.tzOffset = tzOffset;
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
     * @return The isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin The is_admin
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return The isOwner
     */
    public boolean isIsOwner() {
        return isOwner;
    }

    /**
     * @param isOwner The is_owner
     */
    public void setIsOwner(boolean isOwner) {
        this.isOwner = isOwner;
    }

    /**
     * @return The isPrimaryOwner
     */
    public boolean isIsPrimaryOwner() {
        return isPrimaryOwner;
    }

    /**
     * @param isPrimaryOwner The is_primary_owner
     */
    public void setIsPrimaryOwner(boolean isPrimaryOwner) {
        this.isPrimaryOwner = isPrimaryOwner;
    }

    /**
     * @return The isRestricted
     */
    public boolean isIsRestricted() {
        return isRestricted;
    }

    /**
     * @param isRestricted The is_restricted
     */
    public void setIsRestricted(boolean isRestricted) {
        this.isRestricted = isRestricted;
    }

    /**
     * @return The isUltraRestricted
     */
    public boolean isIsUltraRestricted() {
        return isUltraRestricted;
    }

    /**
     * @param isUltraRestricted The is_ultra_restricted
     */
    public void setIsUltraRestricted(boolean isUltraRestricted) {
        this.isUltraRestricted = isUltraRestricted;
    }

    /**
     * @return The isBot
     */
    public boolean isIsBot() {
        return isBot;
    }

    /**
     * @param isBot The is_bot
     */
    public void setIsBot(boolean isBot) {
        this.isBot = isBot;
    }

    /**
     * @return The has2fa
     */
    public boolean isHas2fa() {
        return has2fa;
    }

    /**
     * @param has2fa The has_2fa
     */
    public void setHas2fa(boolean has2fa) {
        this.has2fa = has2fa;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
