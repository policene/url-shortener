package com.policene.url_shortener.controllers;

import com.policene.url_shortener.dtos.LinkStatsResponseDTO;
import com.policene.url_shortener.dtos.UrlShortenerRequestDTO;
import com.policene.url_shortener.dtos.UrlShortenerResponseDTO;
import com.policene.url_shortener.services.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LinkController {

    private final LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlShortenerResponseDTO> shorten (@RequestBody UrlShortenerRequestDTO request) {
        return new ResponseEntity<>(linkService.shorten(request), HttpStatus.CREATED);
    }

    @GetMapping("/stats/{slug}")
    public ResponseEntity<LinkStatsResponseDTO> getLinkStats (@PathVariable String slug) {
        return ResponseEntity.ok(linkService.getLinkStats(slug));
    }

    @DeleteMapping("/links/{slug}")
    public ResponseEntity<Void> deleteLink (@PathVariable String slug) {
        linkService.deleteLink(slug);
        return ResponseEntity.noContent().build();
    }

}
