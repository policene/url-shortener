package com.policene.url_shortener.controllers;

import com.policene.url_shortener.services.RedirectService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping
public class RedirectController {

    private final RedirectService redirectService;

    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Void> redirect (@PathVariable String slug) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirectService.redirectService(slug)));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }


}
