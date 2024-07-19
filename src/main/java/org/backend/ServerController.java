package org.backend;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {

    @GetMapping("/hello")
    public String hello(Model model) {
        return "Hello, World.";
    }
}
