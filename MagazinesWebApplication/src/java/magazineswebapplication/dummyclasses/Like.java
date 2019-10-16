/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dummyclasses;

/**
 *
 * @author zofia
 */
public class Like {
    private String subscriptionId;
    private boolean liked;

    public Like(String subscriptionId, boolean liked) {
        this.subscriptionId = subscriptionId;
        this.liked = liked;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
    
}
