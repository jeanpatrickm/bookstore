package com.bookstore.jpa.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.bookstore.jpa.dtos.AuthorRecordDto;
import com.bookstore.jpa.models.AuthorModel;
import com.bookstore.jpa.repositories.AuthorRepository;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorModel> getAllAuthors() {
        return authorRepository.findAll();
    }

    public AuthorModel saveAuthor(AuthorRecordDto authorRecordDto) {
        AuthorModel author = new AuthorModel();
        author.setName(authorRecordDto.name());
        return authorRepository.save(author);
    }

    public AuthorModel updateAuthor(UUID id, AuthorRecordDto authorRecordDto) {
        AuthorModel existingAuthor = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        existingAuthor.setName(authorRecordDto.name());
        return authorRepository.save(existingAuthor);
    }

    public void deleteAuthor(UUID id) {
        authorRepository.deleteById(id);
    }
}
