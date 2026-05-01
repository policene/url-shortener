package com.policene.url_shortener.repositories;

import com.policene.url_shortener.models.Link;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LinkRepository extends JpaRepository<Link, Long> {

    Optional<Link> findBySlug (String slug);

    @Modifying @Transactional
    @Query("UPDATE Link l SET l.clickCount = l.clickCount + 1 WHERE l.slug = :slug")
    void incrementClickCount(@Param("slug") String slug);

}
