package samryong.blogserver.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import samryong.blogserver.model.Post;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FilePostRepository implements PostRepository{

    private final File file  = new File("./src/main/java/samryong/blogserver/repository/posts.json");
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FilePostRepository(){
        objectMapper.registerModule(new JavaTimeModule());
    }
    @Override
    public List<Post> findAll() {
        if (!file.exists()) return new ArrayList<>();
        try {
            return objectMapper.readValue(file, new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Post> findById(Long id) {
        return findAll().stream().filter(post -> post.getId().equals(id)).findFirst();
    }

    @Override
    public void save(Post post) {
        List<Post> posts = findAll();
        posts.removeIf(p -> p.getId().equals(post.getId()));
        posts.add(post);
        writeToFile(posts);
    }

    @Override
    public void deleteById(Long id) {
        List<Post> posts = findAll();
        posts.removeIf(post -> post.getId().equals(id));
        writeToFile(posts);
    }

    private void writeToFile(List<Post> posts) {
        try {
            objectMapper.writeValue(file, posts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
