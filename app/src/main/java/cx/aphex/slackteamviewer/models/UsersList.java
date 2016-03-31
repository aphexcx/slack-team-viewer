
package cx.aphex.slackteamviewer.models;

import java.util.ArrayList;
import java.util.List;

//@Generated("org.jsonschema2pojo")
public class UsersList {

    private boolean ok;
    private List<Member> members = new ArrayList<Member>();
    private int cache_ts;

    /**
     * @return The ok
     */
    public boolean isOk() {
        return ok;
    }

    /**
     * @param ok The ok
     */
    public void setOk(boolean ok) {
        this.ok = ok;
    }

    /**
     * @return The members
     */
    public List<Member> getMembers() {
        return members;
    }

    /**
     * @param members The members
     */
    public void setMembers(List<Member> members) {
        this.members = members;
    }

    /**
     * @return The cache_ts
     */
    public int getCache_ts() {
        return cache_ts;
    }

    /**
     * @param cache_ts The cache_ts
     */
    public void setCache_ts(int cache_ts) {
        this.cache_ts = cache_ts;
    }

}
