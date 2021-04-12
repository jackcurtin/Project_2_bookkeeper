package com.bookkeeper.demo.service;

import com.bookkeeper.demo.exception.InformationExistsException;
import com.bookkeeper.demo.exception.InformationNotFoundException;
import com.bookkeeper.demo.model.Genre;
import com.bookkeeper.demo.model.Publisher;
import com.bookkeeper.demo.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Publisher getPublisher(Long publisherId){
        System.out.println("Service calling getPublisher");
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);
        if (publisher.isPresent()){
            return publisher.get();
        } else{
            throw new InformationNotFoundException("Publisher with ID " + publisherId + " not found");
        }
    }

    public Publisher addPublisher(Publisher publisherObject){
        System.out.println("service calling addPublisher");
        Optional<Publisher> publisherChecker = publisherRepository.findByName(publisherObject.getName());
        if(publisherChecker.isPresent()){
            throw new InformationExistsException("Publisher named "+ publisherObject.getName() + " already in database.");
        }else{
            return publisherRepository.save(publisherObject);
        }
    }

    public ResponseEntity<Object> deletePublisher(Long publisherId){
        System.out.println("Service is calling deletePublisher");
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);
        if(publisher.isPresent()){
            publisherRepository.deleteById(publisherId);
            return ResponseEntity.ok(HttpStatus.OK);
        } else{
            throw new InformationNotFoundException("Publisher with ID "+ publisherId +" not found.");
        }
    }
}
