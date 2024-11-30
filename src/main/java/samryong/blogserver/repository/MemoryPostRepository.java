package samryong.blogserver.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import samryong.blogserver.model.Post;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryPostRepository implements PostRepository{

    private final List<Post> posts = new ArrayList<>();

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return posts.stream().filter(post -> post.getId().equals(id)).findFirst();
    }

    @Override
    public List<Post> findByCategory(String category) {
        List<Post> result = new ArrayList<>();
        for (Post post : posts) {
            if (post.getCategory().equals(category)){
                result.add(post);
            }
        }
        return result;
    }

    @Override
    public void save(Post post) {
        posts.removeIf(p -> p.getId().equals(post.getId()));
        posts.add(post);
    }

    @Override
    public void deleteById(Long id) {
        posts.removeIf(post -> post.getId().equals(id));
    }

}
