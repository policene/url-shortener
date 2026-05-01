package com.policene.url_shortener.services;

import com.policene.url_shortener.dtos.UrlShortenerRequest;
import com.policene.url_shortener.dtos.UrlShortenerResponse;
import com.policene.url_shortener.models.Link;
import com.policene.url_shortener.repositories.LinkRepository;
import com.policene.url_shortener.utils.SlugGenerator;
import org.hibernate.query.common.TemporalUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public UrlShortenerResponse shorten (UrlShortenerRequest request) {

        Link link = Link
                .builder()
                .slug(SlugGenerator.generateSlug())
                .targetUrl(request.url())
                .build();

        linkRepository.save(link);

        return new UrlShortenerResponse(baseUrl + "/" + link.getSlug());

    }

}
