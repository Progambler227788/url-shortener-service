package com.talhaatif.urlshortener.repository;

import com.talhaatif.urlshortener.model.ShortenedUrl;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


// URL as value, String as a key that is ID
public interface UrlRepository extends MongoRepository<ShortenedUrl,String> {
    Optional<ShortenedUrl> findByShortCode(String shortCode);
}
