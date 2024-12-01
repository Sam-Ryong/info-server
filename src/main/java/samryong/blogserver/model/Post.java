package samryong.blogserver.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private Long id;
    private String url;
    private String title;
    private String tag;
    private LocalDateTime createdAt;
}
