package com.bookkeeper.demo.controller;

import com.bookkeeper.demo.model.Publisher;
import com.bookkeeper.demo.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
    private PublisherService publisherService;

    @Autowired
    public void setPublisherService(PublisherService publisherService){
        this.publisherService = publisherService;
    }

    @GetMapping
    public List<Publisher> getAllPublishers(){
        System.out.println("calling getAllPublishers");
        return publisherService.getAllPublishers();
    }

}
