package samryong.blogserver.repository;

import org.springframework.stereotype.Repository;
import samryong.blogserver.model.Post;
import samryong.blogserver.model.Visit;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public interface VisitRepository {

    Visit getVisit();
    void addToday();
    void resetToday();
}
