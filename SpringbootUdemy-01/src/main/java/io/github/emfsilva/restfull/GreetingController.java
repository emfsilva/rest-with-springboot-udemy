package io.github.emfsilva.restfull;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    @GetMapping
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "world")String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
