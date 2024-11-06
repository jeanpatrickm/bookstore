package com.bookstore.jpa.dtos;

import java.util.Set;
import java.util.UUID;

public record BookRecordDto(String title,
                            Double price,
                            UUID publisherId,
                            Set<UUID> authorIds,
                            String reviewComment,
                            String authorName,
                            String publisherName) {
}