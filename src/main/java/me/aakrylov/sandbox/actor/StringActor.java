package me.aakrylov.sandbox.actor;

import akka.actor.AbstractActor;
import akka.actor.Status;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class StringActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, s -> {
                    try {
                        log.info("I've received this String: {}", s);
                        if (s.length() <= 25) {
                            String reverse = StringUtils.reverse(s);
                            getSender().tell(reverse, getSelf());
                        } else {
                            throw new RuntimeException("String is too long to reverse");
                        }
                    } catch (Exception e) {
                        getSender().tell(
                                new Status.Failure(e), getSelf());
                        throw e;
                    }
                })
                .matchAny(o -> log.error("This object={} is not a String!", o))
                .build();
    }
}
