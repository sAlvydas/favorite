package com.app.favorite.mapper;

import com.app.favorite.domain.Album;
import com.app.favorite.dto.album.AlbumDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class AlbumMapper {

    private static final String ARTIST = "artist";

    private Predicate<AlbumDto> isArtistType = a -> ARTIST.equals(a.getWrapperType());

    public List<Album> mapAlbums(List<AlbumDto> albumDtos) {
        List<Album> albums = new ArrayList<>();
        for (AlbumDto albumDto : albumDtos) {
            if (ARTIST.equals(albumDto.getWrapperType())) {
                continue;
            }
            Album album = new Album();
            album.setArtistId(albumDto.getArtistId());
            album.setArtistName(albumDto.getArtistName());
            album.setAmgArtistId(albumDto.getAmgArtistId());
            album.setCollectionId(albumDto.getCollectionId());
            albums.add(album);
        }
        return albums;
    }
}
