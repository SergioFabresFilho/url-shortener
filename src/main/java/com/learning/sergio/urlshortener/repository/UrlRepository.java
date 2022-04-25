package com.learning.sergio.urlshortener.repository;

import com.learning.sergio.urlshortener.domain.entity.Url;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UrlRepository extends CrudRepository<Url, Long> {
    Optional<Url> findByShortenedUrl(String shortenedUrl);

    @Async
    @Transactional
    @Modifying
    @Query(value = "UPDATE Url u SET u.clickCount = u.clickCount + 1 WHERE u.id = :id")
    void incrementClickCount(@Param("id") Long id);

    @Async
    @Override
    void deleteById(Long id);

    boolean existsByShortenedUrl(String shortenedUrl);
}
