package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {
    @Autowired
    ApplicationContext appContext;
    @GetMapping("/")
    public String home() {
        return "Running.";
    }
    @GetMapping("/exit")
    public String exit(@RequestParam("code") int code) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
            SpringApplication.exit(appContext, () -> code);
            System.exit(code);
        });
        t.setDaemon(false);
        t.start();
        return "Exiting(code="+code+")..";
    }
}
