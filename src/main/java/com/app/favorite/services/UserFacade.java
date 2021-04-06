package com.app.favorite.services;

import com.app.favorite.clients.ItunesClient;
import com.app.favorite.domain.Album;
import com.app.favorite.dto.artist.Artist;
import com.app.favorite.mapper.AlbumMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {

    private static final Logger logger = LoggerFactory.getLogger(UserFacade.class);

    private final AlbumService albumService;

    private final ArtistService artistService;

    private final AlbumMapper albumMapper;

    private final ItunesClient itunesClient;

    public UserFacade(AlbumService albumService, ArtistService artistService, AlbumMapper albumMapper, ItunesClient itunesClient) {
        this.albumService = albumService;
        this.artistService = artistService;
        this.albumMapper = albumMapper;
        this.itunesClient = itunesClient;
    }

    public void prepareAlbumsForUser(int amgArtistId, int userId) {
        artistService.saveArtist(amgArtistId, userId);
        if (albumService.checkIfExist(amgArtistId)) {
            logger.info("{} exists in database", amgArtistId);
            return;
        }
        logger.info("amgArtistId: {} does not exist in database, will be retrieved from itunes", amgArtistId);
        List<Album> albums = albumMapper.mapAlbums(itunesClient.getAlbums(amgArtistId));
        albumService.saveAlbums(albums);
    }

    public List<Artist> getArtists(String artistName) {
        return artistService.getArtists(artistName);
    }
}
