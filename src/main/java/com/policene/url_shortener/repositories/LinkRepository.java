package com.policene.url_shortener.repositories;

import com.policene.url_shortener.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Long> { }
