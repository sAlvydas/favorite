package com.app.favorite.clients;

import com.app.favorite.dto.album.AlbumDto;
import com.app.favorite.dto.album.AlbumsResponse;
import com.app.favorite.dto.artist.ArtistsResponse;
import com.app.favorite.dto.artist.Artist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Service
public class ItunesClient {

    private static final Logger logger = LoggerFactory.getLogger(ItunesClient.class);

    private static final String SEARCH_ARTISTS = "search";
    private static final String LOOKUP_ALBUMS = "lookup";
    private final RestTemplate restTemplate;
    @Value("${itunes.url}")
    private String itunesUrl;

    public ItunesClient(RestTemplate restTemplate, @Value("${itunes.url}") String itunesUrl) {
        this.restTemplate = restTemplate;
        this.itunesUrl = itunesUrl;
    }

    public List<Artist> getArtistsByName(String name) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        URI uri = UriComponentsBuilder.fromHttpUrl(itunesUrl + SEARCH_ARTISTS)
                .queryParam("entity", "allArtist")
                .queryParam("term", name).buildAndExpand().toUri();

        ResponseEntity<ArtistsResponse> response;

        try {
            response = restTemplate.getForEntity(uri, ArtistsResponse.class);
        } catch (Exception e){
            logger.error("Failed to get artists from itunes", e);
            return Collections.emptyList();
        }
        return response.getBody().getArtists();
    }

    public List<AlbumDto> getAlbums(int amgArtistId) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        URI uri = UriComponentsBuilder.fromHttpUrl(itunesUrl + LOOKUP_ALBUMS)
                .queryParam("amgArtistId", amgArtistId)
                .queryParam("entity", "album")
                .queryParam("limit", "5").buildAndExpand().toUri();

        ResponseEntity<AlbumsResponse> response;

        try {
            response = restTemplate.getForEntity(uri, AlbumsResponse.class);
        } catch (Exception e) {
            logger.error("Failed to get albums from itunes", e);
            return null;
        }
        return response.getBody().getAlbums();
    }

}
