package com.bookstore.jpa.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.jpa.dtos.PublisherRecordDto;
import com.bookstore.jpa.models.PublisherModel;
import com.bookstore.jpa.services.PublisherService;

@RestController
@RequestMapping("/bookstore/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }
    
    @GetMapping
    public ResponseEntity<List<PublisherModel>> getAllPublishers() {
        return ResponseEntity.status(HttpStatus.OK).body(publisherService.getAllPublishers());
    }

    @PostMapping
    public ResponseEntity<PublisherModel> savePublisher(@RequestBody PublisherRecordDto publisherRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.savePublisher(publisherRecordDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherModel> updatePublisher(@PathVariable UUID id, @RequestBody PublisherRecordDto publisherRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(publisherService.updatePublisher(id, publisherRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePublisher(@PathVariable UUID id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.status(HttpStatus.OK).body("Publisher deleted successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherModel> getPublisherById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(publisherService.getPublisherById(id));
    }
}
