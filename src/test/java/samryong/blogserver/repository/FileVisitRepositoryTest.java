package samryong.blogserver.repository;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FileVisitRepositoryTest {


    FileVisitRepository fileVisitRepository = new FileVisitRepository();
    @Test
    public void test() {
        System.out.println(fileVisitRepository.getVisit());
        fileVisitRepository.addToday();
        System.out.println(fileVisitRepository.getVisit());
        String cookieValue = LocalDate.now().toString();
        System.out.println(cookieValue);
    }

}