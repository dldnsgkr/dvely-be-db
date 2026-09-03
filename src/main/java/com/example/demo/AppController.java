package com.example.demo;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private final VisitRepository visits;

    public AppController(VisitRepository visits) {
        this.visits = visits;
    }

    /** DB 를 건드리지 않는 헬스체크 — 앱이 떠 있으면 UP. */
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }

    @GetMapping("/")
    public String root() {
        return "dvely-be-db OK";
    }

    /**
     * RDS 연결 증명: 방문 한 행을 쓰고(write) 전체 개수를 읽어(read) 돌려준다.
     * 값이 호출마다 늘면 EC2 앱이 RDS 에 실제로 읽고 쓰는 것이다.
     */
    @GetMapping("/db")
    public Map<String, Object> db() {
        Visit saved = visits.save(new Visit());
        return Map.of("ok", true, "insertedId", saved.getId(), "count", visits.count());
    }
}
