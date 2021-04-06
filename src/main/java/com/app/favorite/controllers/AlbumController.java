package com.app.favorite.controllers;

import com.app.favorite.domain.Album;
import com.app.favorite.services.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(value = "/userId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Album>> getAlbums(@PathVariable("userId") int userId) {
        List<Album> albums = albumService.getAlbums(userId);
        boolean isEmpty = CollectionUtils.isEmpty(albums);
        return new ResponseEntity<>(albums, isEmpty ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
