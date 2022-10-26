package me.aakrylov.sandbox.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import me.aakrylov.sandbox.actor.StringActor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaConfig {

    @Bean
    public ActorSystem actorSystem() {
        return ActorSystem.create("sandbox-actor-system");
    }

    @Bean
    public ActorRef stringReverseActor(ActorSystem actorSystem) {
        return actorSystem.actorOf(Props.create(StringActor.class), "string-reverse-actor");
    }
}
