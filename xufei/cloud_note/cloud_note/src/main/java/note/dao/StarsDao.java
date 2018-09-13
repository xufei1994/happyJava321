package note.dao;

import note.entity.Stars;

public interface StarsDao {
    Stars findStarsByUserId(String userId);
    int insertStars(Stars stars);
    int updateStars(Stars stars);

}
