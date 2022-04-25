package com.learning.sergio.urlshortener.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "shortened_url", unique = true, nullable = false)
    private String shortenedUrl;

    @Column(name = "full_url", nullable = false)
    private String fullUrl;

    @Column(name = "click_count", nullable = false)
    private Long clickCount;

    @Column(name = "click_count_limit")
    private Long clickCountLimit;

    @Column(name = "date_time_limit")
    private LocalDateTime dateTimeLimit;

    public Url(Long id, String shortenedUrl, String fullUrl, @Nullable Long clickCountLimit, @Nullable LocalDateTime dateTimeLimit) {
        this.id = id;
        this.shortenedUrl = shortenedUrl;
        this.fullUrl = fullUrl;
        this.clickCount = 0L;
        this.clickCountLimit = clickCountLimit;
        this.dateTimeLimit = dateTimeLimit;
    }
}
