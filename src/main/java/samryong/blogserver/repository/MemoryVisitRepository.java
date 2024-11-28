package samryong.blogserver.repository;

import org.springframework.stereotype.Repository;
import samryong.blogserver.model.Visit;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryVisitRepository implements VisitRepository{

    private final Visit visit = new Visit();


    @Override
    public Visit getVisit() {
        return visit;
    }


    @Override
    public void addToday() {
        visit.setToday(visit.getToday() + 1);
    }

    @Override
    public void resetToday() {
        visit.setToday(0L);
    }
}
