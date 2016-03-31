
package cx.aphex.slackteamviewer.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Generated("org.jsonschema2pojo")
public class UsersList {

    private boolean ok;
    private List<Member> members = new ArrayList<Member>();
    private int cacheTs;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The ok
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * 
     * @param ok
     *     The ok
     */
    public void setOk(boolean ok) {
        this.ok = ok;
    }

    /**
     * 
     * @return
     *     The members
     */
    public List<Member> getMembers() {
        return members;
    }

    /**
     * 
     * @param members
     *     The members
     */
    public void setMembers(List<Member> members) {
        this.members = members;
    }

    /**
     * 
     * @return
     *     The cacheTs
     */
    public int getCacheTs() {
        return cacheTs;
    }

    /**
     * 
     * @param cacheTs
     *     The cache_ts
     */
    public void setCacheTs(int cacheTs) {
        this.cacheTs = cacheTs;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
