package note.dao;

import note.entity.Post;

public interface PostDao {
	Post findPostById(Integer id);
}
