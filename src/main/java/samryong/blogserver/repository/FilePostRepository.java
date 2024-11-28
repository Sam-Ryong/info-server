package samryong.blogserver.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import samryong.blogserver.model.Post;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FilePostRepository implements PostRepository{

    private final String resourcePath = "posts.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FilePostRepository(){
        objectMapper.registerModule(new JavaTimeModule());
    }
    @Override
    public List<Post> findAll() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                System.out.println("Resource not found: " + resourcePath);
                return new ArrayList<>();
            }
            return objectMapper.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
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
        try (OutputStream outputStream = new FileOutputStream(getClass().getClassLoader().getResource(resourcePath).getFile())) {
            objectMapper.writeValue(outputStream, posts);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
