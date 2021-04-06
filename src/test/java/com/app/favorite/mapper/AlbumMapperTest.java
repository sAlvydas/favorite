package com.app.favorite.mapper;

import com.app.favorite.domain.Album;
import com.app.favorite.dto.album.AlbumDto;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AlbumMapperTest {

    private AlbumMapper albumMapper;

    @Before
    public void setup() {
        albumMapper = new AlbumMapper();
    }

    @Test
    public void testMapAlbums() {
        AlbumDto albumDto = getAlbumDto();

        List<Album> albums = albumMapper.mapAlbums(List.of(albumDto));
        Album album = albums.get(0);

        assertEquals("ABBA", album.getArtistName());
        assertEquals(123, album.getArtistId());
        assertEquals(987, album.getAmgArtistId());
        assertEquals(852, album.getCollectionId());
    }

    private AlbumDto getAlbumDto() {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setArtistName("ABBA");
        albumDto.setArtistId(123);
        albumDto.setAmgArtistId(987);
        albumDto.setCollectionId(852);
        return albumDto;
    }
}
