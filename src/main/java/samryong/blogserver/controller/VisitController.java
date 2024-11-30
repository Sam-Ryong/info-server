package samryong.blogserver.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import samryong.blogserver.model.Visit;
import samryong.blogserver.service.VisitService;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/visit")
@AllArgsConstructor
public class VisitController {
    private final VisitService visitService;
    @GetMapping
    public Visit getVisit(HttpServletRequest request, HttpServletResponse response){
        String cookieName = "visitorCookie";
        String cookieValue = LocalDate.now().toString();

        boolean alreadyVisited = false;

        // 쿠키 확인
        Cookie[] cookies = request.getCookies();;
        if (cookies != null) {
            for (Cookie cookie : cookies) {

                if (cookieName.equals(cookie.getName())) {

                    alreadyVisited = true;


                }
            }
        }
        // 쿠키가 없으면 생성
        if (!alreadyVisited) {
            visitService.visit();
            Cookie newCookie = new Cookie(cookieName, cookieValue);
            newCookie.setMaxAge(24 * 60 * 60); // 1일 동안 유효
            newCookie.setPath("/"); // 전체 경로에서 유효
            response.addCookie(newCookie);
        }

        return visitService.getVisit();
    }
}
