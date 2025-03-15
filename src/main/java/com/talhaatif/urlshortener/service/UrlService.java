package com.talhaatif.urlshortener.service;

import com.talhaatif.urlshortener.model.ShortenedUrl;
import com.talhaatif.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static  final  int CHARACTERS_LENGTH = CHARACTERS.length();
    private static final int SHORT_CODE_LENGTH = 6;
    private static final SecureRandom random = new SecureRandom();



    // Generate Short Code for URL
    private String generateShortCode() {
        StringBuilder sb = new StringBuilder(SHORT_CODE_LENGTH);
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(  random.nextInt(CHARACTERS_LENGTH ) ) );
        }
        return sb.toString();
    }

    // Create Short URL
    public ShortenedUrl createShortUrl(String longUrl) {
        String shortCode;
        do {
            shortCode = generateShortCode();
        } while (urlRepository.findByShortCode(shortCode).isPresent());

        ShortenedUrl newUrl = new ShortenedUrl();
        newUrl.setLongUrl(longUrl);
        newUrl.setShortCode(shortCode);

        return urlRepository.save(newUrl);
    }

    // Retrieve Original URL
    public Optional<ShortenedUrl> getOriginalUrl(String shortCode) {
        Optional<ShortenedUrl> shortenedUrl = urlRepository.findByShortCode(shortCode);
        shortenedUrl.ifPresent(url -> {
            url.setAccessCount(url.getAccessCount() + 1);
            urlRepository.save(url);
        });
        return shortenedUrl;
    }

    // Update Short URL
    public Optional<ShortenedUrl> updateShortUrl(String shortCode, String newLongUrl) {
        Optional<ShortenedUrl> existingUrl = urlRepository.findByShortCode(shortCode);
        existingUrl.ifPresent(url -> {
            url.setLongUrl(newLongUrl);
            url.setUpdatedAt(java.time.Instant.now());
            urlRepository.save(url);
        });
        return existingUrl;
    }

    // Delete Short URL
    public boolean deleteShortUrl(String shortCode) {
        Optional<ShortenedUrl> url = urlRepository.findByShortCode(shortCode);
        url.ifPresent(urlRepository::delete);
        return url.isPresent();
    }

    // Get URL Statistics
    public Optional<ShortenedUrl> getUrlStats(String shortCode) {

        return urlRepository.findByShortCode(shortCode);
    }
}

