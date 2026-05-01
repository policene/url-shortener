package com.policene.url_shortener.services;

import com.policene.url_shortener.dtos.LinkStatsResponseDTO;
import com.policene.url_shortener.dtos.UrlShortenerRequestDTO;
import com.policene.url_shortener.dtos.UrlShortenerResponseDTO;
import com.policene.url_shortener.mappers.LinkMapper;
import com.policene.url_shortener.models.Link;
import com.policene.url_shortener.repositories.LinkRepository;
import com.policene.url_shortener.utils.SlugGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    @Value("${app.base-url}")
    private String baseUrl;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link findBySlug (String slug) {
        return linkRepository.findBySlug(slug)
                .orElseThrow(() -> new NoSuchElementException("Link not found for the slug: " + slug));
    }

    public void incrementClickCount (String slug) {
        linkRepository.incrementClickCount(slug);
    }

    public UrlShortenerResponseDTO shorten (UrlShortenerRequestDTO request) {

        Link link = Link
                .builder()
                .slug(SlugGenerator.generateSlug())
                .targetUrl(request.url())
                .build();

        linkRepository.save(link);

        return new UrlShortenerResponseDTO(baseUrl + "/" + link.getSlug());

    }

    public LinkStatsResponseDTO getLinkStats (String slug) {
        Link foundLink = findBySlug(slug);
        return LinkMapper.toStatsDTO(foundLink);
    }

    public void deleteLink (String slug) {
        Link foundLink = findBySlug(slug);
        linkRepository.delete(foundLink);
    }

}
