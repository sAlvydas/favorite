package com.app.favorite.services;

import com.app.favorite.domain.Album;
import com.app.favorite.domain.repositories.AlbumRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumServiceTest {

    @Autowired
    private AlbumRepository albumRepository;

    private AlbumService albumService;

    @Before
    public void setup() {
        albumService = new AlbumService(albumRepository);
    }

    @Test
    public void testSaveAlbums() {
        Album album = new Album(123, 888, 963, "ABBA");
        Album album1 = new Album(777, 888, 999, "QUEEN");
        albumService.saveAlbums(List.of(album, album1));
        assertTrue(albumRepository.existsByAmgArtistId(888));
    }

    @Test
    public void testGetAlbums() {
        List<Album> albums = albumService.getAlbums(1);
        Album album = albums.get(0);
        assertNotNull(album);
        assertEquals(888, album.getAmgArtistId());
        assertEquals("ABBA", album.getArtistName());
        assertEquals(88, album.getCollectionId());
        assertEquals(65, album.getArtistId());
    }

}
