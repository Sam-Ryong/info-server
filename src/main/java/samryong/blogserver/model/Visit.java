package samryong.blogserver.model;

import lombok.Data;

@Data
public class Visit {
    private Long today = 0L;
    private Long total = 0L;
}
