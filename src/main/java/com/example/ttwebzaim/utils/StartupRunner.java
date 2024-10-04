package com.example.ttwebzaim.utils;

import com.example.ttwebzaim.services.RequestContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final RequestContentService requestProcessingService;

    @Autowired
    public StartupRunner(RequestContentService requestProcessingService) {
        this.requestProcessingService = requestProcessingService;
    }

    @Override
    public void run(String... args) {
        requestProcessingService.processRequests();
    }
}