package samryong.blogserver.repository;

import org.springframework.stereotype.Repository;
import samryong.blogserver.model.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository {
    List<Post> findAll();
    Optional<Post> findById(Long id);
    void save(Post post);
    void deleteById(Long id);
}
