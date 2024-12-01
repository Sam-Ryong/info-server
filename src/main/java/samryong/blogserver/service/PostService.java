package samryong.blogserver.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import samryong.blogserver.model.Post;
import samryong.blogserver.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getPostByUrl(String url) {
        return postRepository.findByUrl(url);
    }

    public Post createPost(Post post) {
        post.setId(System.currentTimeMillis());
        post.setCreatedAt(LocalDateTime.now());
        postRepository.save(post);
        return post;
    }

    public Post updatePost(Long id, Post updatedPost) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            postRepository.save(post);
            return post;
        }
        throw new RuntimeException("Post not found");
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

}
