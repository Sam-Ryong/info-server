package samryong.blogserver.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import samryong.blogserver.model.Post;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FileVisitRepository implements VisitRepository{

    private final File file  = new File(getClass().getClassLoader().getResource("visit.json").getFile());
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FileVisitRepository(){
        objectMapper.registerModule(new JavaTimeModule());
    }



    @Override
    public ConcurrentHashMap<String,Long> getVisit() {
        try {
            return objectMapper.readValue(file, new TypeReference<ConcurrentHashMap<String, Long>>() {
            });
        } catch (IOException e) {
            return new ConcurrentHashMap<>();
        }
    }


    @Override
    public void addToday() {
        ConcurrentHashMap<String, Long> visit = getVisit();
        visit.replace("today",visit.get("today") + 1);
        visit.replace("total",visit.get("total") + 1);
        writeToFile(visit);
    }

    @Override
    public void resetToday() {
        ConcurrentHashMap<String, Long> visit = getVisit();
        visit.replace("total",visit.get("total") + visit.get("today"));
        visit.replace("today",0L);
        writeToFile(visit);
    }
    private void writeToFile(ConcurrentHashMap<String, Long> visit) {
        try {
            objectMapper.writeValue(file, visit);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
