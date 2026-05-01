package com.policene.url_shortener.services;

import com.policene.url_shortener.exceptions.ExpiredLinkException;
import com.policene.url_shortener.models.Link;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RedirectService {

    private final LinkService linkService;

    public RedirectService(LinkService linkService) {
        this.linkService = linkService;
    }

    public String redirectService (String slug) {

        Link linkFound = linkService.findBySlug(slug);

        if (linkFound.getExpiresAt().isBefore(Instant.now())) {
            throw new ExpiredLinkException();
        } else {
            linkService.incrementClickCount(slug);
            return linkFound.getTargetUrl();
        }

    }

}
