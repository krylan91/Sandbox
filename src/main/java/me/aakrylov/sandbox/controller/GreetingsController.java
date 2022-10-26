package me.aakrylov.sandbox.controller;

import lombok.RequiredArgsConstructor;
import me.aakrylov.sandbox.service.api.GreetingsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
@RequiredArgsConstructor
public class GreetingsController {

    private final GreetingsService greetingsService;

    @GetMapping("/")
    public String greeting() {
        return greetingsService.defaultGreeting();
    }

    @GetMapping("/personal")
    public String namedGreeting() {
        return greetingsService.namedGreeting();
    }

    @GetMapping("/delayed")
    public String delayedGreeting() { return greetingsService.delayedGreeting(); }
}
