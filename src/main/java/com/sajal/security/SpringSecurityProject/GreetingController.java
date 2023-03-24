package com.sajal.security.SpringSecurityProject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greet")
public class GreetingController {
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from Sajal");
    }

    @GetMapping("/bye")
    public ResponseEntity<String> sayBye() {
        return ResponseEntity.ok("bye bye Sajal");
    }
}