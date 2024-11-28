package samryong.blogserver.service;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import samryong.blogserver.repository.FileVisitRepository;
import samryong.blogserver.repository.PostRepository;
import samryong.blogserver.repository.VisitRepository;

import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;

    public ConcurrentHashMap<String, Long> getVisit(){
        return visitRepository.getVisit();
    }

    public void visit() {
        visitRepository.addToday();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetToday() {
        visitRepository.resetToday();
    }
}
