package samryong.blogserver.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Post {
    private Long id;
    private String url;
    private String title;
    private String tag;
    private Map<String,Object> content;
    private LocalDateTime createdAt;
}
