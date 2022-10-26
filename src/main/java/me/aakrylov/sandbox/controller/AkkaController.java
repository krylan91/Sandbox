package me.aakrylov.sandbox.controller;

import akka.actor.ActorRef;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static akka.pattern.PatternsCS.ask;

@RestController
@RequestMapping("/akka")
@RequiredArgsConstructor

public class AkkaController {

    private final ActorRef stringReverseActor;

    @GetMapping("/reverse/{string}")
    public CompletableFuture<Object> akkaString(@PathVariable("string") String str) {
        return ask(stringReverseActor, str, 1000).toCompletableFuture();
    }
}
