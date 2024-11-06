package com.bookstore.jpa.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bookstore.jpa.dtos.PublisherRecordDto;
import com.bookstore.jpa.models.PublisherModel;
import com.bookstore.jpa.repositories.PublisherRepository;

import jakarta.transaction.Transactional;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<PublisherModel> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @Transactional
    public PublisherModel savePublisher(PublisherRecordDto publisherRecordDto) {
        PublisherModel publisher = new PublisherModel();
        publisher.setName(publisherRecordDto.name());
        return publisherRepository.save(publisher);
    }
  
    @Transactional
    public PublisherModel updatePublisher(UUID id, PublisherRecordDto publisherRecordDto) {
        PublisherModel existingPublisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
        existingPublisher.setName(publisherRecordDto.name());
        return publisherRepository.save(existingPublisher);
    }

    @Transactional
    public void deletePublisher(UUID id) {
        publisherRepository.deleteById(id);
    }

    public PublisherModel getPublisherById(UUID id) {
        return publisherRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Publisher not found"));
    }
}
