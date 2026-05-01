package com.policene.url_shortener.controllers;

import com.policene.url_shortener.dtos.UrlShortenerRequest;
import com.policene.url_shortener.dtos.UrlShortenerResponse;
import com.policene.url_shortener.services.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlShortenerResponse> shorten (@RequestBody UrlShortenerRequest request) {
        return new ResponseEntity<>( linkService.shorten(request), HttpStatus.CREATED);
    }

}
