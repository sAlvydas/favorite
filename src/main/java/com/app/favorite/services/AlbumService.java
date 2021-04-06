package com.app.favorite.services;

import com.app.favorite.clients.ItunesClient;
import com.app.favorite.domain.Album;
import com.app.favorite.domain.repositories.AlbumRepository;
import com.app.favorite.dto.album.AlbumDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> getAlbums(int userId) {
        return albumRepository.findAlbums(userId);
    }

    public void saveAlbums(List<Album> albums) {
        albumRepository.saveAll(albums);
    }

    public boolean checkIfExist(int amgArtistId) {
        return albumRepository.existsByAmgArtistId(amgArtistId);
    }

}
