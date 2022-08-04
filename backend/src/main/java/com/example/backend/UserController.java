package com.example.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("me")
    String me(Principal principal) {
        if (principal == null) {
            return "Not logged in";
        }
        return principal.getName();
    }
}
