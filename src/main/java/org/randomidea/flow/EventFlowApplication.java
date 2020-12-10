package org.randomidea.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.randomidea.flow")
public class EventFlowApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventFlowApplication.class, args);
    }
}
