package com.bookkeeper.demo.service;

import com.bookkeeper.demo.exception.InformationNotFoundException;
import com.bookkeeper.demo.model.Publisher;
import com.bookkeeper.demo.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    private PublisherRepository publisherRepository;

    @Autowired
    public void setPublisherRepository(PublisherRepository publisherRepository){
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> getAllPublishers(){
        System.out.println("service calling getAllPublishers");
        List<Publisher> publishers = publisherRepository.findAll();
        if(publishers.isEmpty()){
            throw new InformationNotFoundException("No publishers in the database");
        }
        return publishers;
    }
}
