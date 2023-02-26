package com.christo;

import com.christo.rabbitMQ.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BasicApplication {
    @Autowired
    private RabbitMQService rabbitMQInterface;
    
    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }
    
    @EventListener(ApplicationReadyEvent.class)
    public void doStartup() {
        rabbitMQInterface.start();
    }
}
