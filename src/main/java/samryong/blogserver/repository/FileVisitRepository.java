package samryong.blogserver.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import samryong.blogserver.model.Post;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FileVisitRepository implements VisitRepository{

    private final String resourcePath = "visit.json";  // 리소스 경로
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FileVisitRepository(){
        objectMapper.registerModule(new JavaTimeModule());
    }



    @Override
    public ConcurrentHashMap<String,Long> getVisit() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                System.out.println("Resource not found: " + resourcePath);
                return new ConcurrentHashMap<>();
            }
            return objectMapper.readValue(inputStream, new TypeReference<ConcurrentHashMap<String, Long>>() {});
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
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
            // 파일을 쓸 때는 클래스패스 내의 실제 경로에 접근해야 하므로
            // JAR 환경에서는 별도의 파일 시스템 경로를 설정하거나 외부 파일에 쓰는 방식으로 구현해야 할 수 있습니다.
            File file = new File(getClass().getClassLoader().getResource(resourcePath).toURI());
            objectMapper.writeValue(file, visit);
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
