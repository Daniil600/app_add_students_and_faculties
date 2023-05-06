package skypro.liberyofhogwarts.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class InfoController {
    @Value("${server.port}")
    private Integer PORT;

    @GetMapping
    public ResponseEntity<Integer> getPort(){
        return ResponseEntity.ok(PORT);
    }
}
