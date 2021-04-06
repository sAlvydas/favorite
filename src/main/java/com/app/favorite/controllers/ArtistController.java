package com.app.favorite.controllers;

import com.app.favorite.dto.artist.Artist;
import com.app.favorite.services.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/artists")
public class ArtistController {

    private static final Logger logger = LoggerFactory.getLogger(ArtistController.class);

    private final UserFacade userFacade;

    public ArtistController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping(value = "/amgArtistId/{amgArtistId}/userId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveFavoriteArtist(@PathVariable("amgArtistId") int amgArtistId, @PathVariable("userId") int userId) {
        userFacade.prepareAlbumsForUser(amgArtistId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{artistName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artist>> getArtists(@PathVariable("artistName") String artistName) {
        List<Artist> artists = userFacade.getArtists(artistName);
        boolean isEmpty = CollectionUtils.isEmpty(artists);
        return new ResponseEntity<>(artists, isEmpty? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
