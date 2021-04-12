package com.bookkeeper.demo.controller;

import com.bookkeeper.demo.model.Genre;
import com.bookkeeper.demo.model.Publisher;
import com.bookkeeper.demo.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping
//    public Publisher addPublisher(@ResponseBody Publisher publisherObject){
//        System.out.println("calling addPublisher");
//        return publisherService.addPublisher(publisherObject);
//    }

    @PostMapping
    public Publisher addPublisher(@RequestBody Publisher publisherObject){
        System.out.println("Calling addPublisher");
        return publisherService.addPublisher(publisherObject);
    }
}
