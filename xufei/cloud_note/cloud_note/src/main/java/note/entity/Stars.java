package note.entity;

import java.io.Serializable;

public class Stars implements Serializable {

    private static final long serialVersionUID = -6413387957368166542L;
    private String id;
    private String userId;
    private int stars;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Stars() {

    }

    public Stars(String id, String userId, int stars) {
        this.id = id;
        this.userId = userId;
        this.stars = stars;
    }
}
