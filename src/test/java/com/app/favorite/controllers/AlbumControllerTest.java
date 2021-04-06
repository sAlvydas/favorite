package com.app.favorite.controllers;

import com.app.favorite.domain.Album;
import com.app.favorite.services.AlbumService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AlbumControllerTest {

    private static final String FIND_ALBUMS_ENDPOINT = "/albums/userId/{userId}";

    private AlbumService albumService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        albumService = mock(AlbumService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new AlbumController(albumService)).build();
    }

    @Test
    public void testGetAlbums_returnsStatus_OK() throws Exception {
        List<Album> albums = getAlbums();
        given(albumService.getAlbums(anyInt())).willReturn(albums);

        mockMvc.perform(get(FIND_ALBUMS_ENDPOINT, "5464").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAlbums_returnsStatus_NOT_FOUND() throws Exception {
        when(albumService.getAlbums(anyInt())).thenReturn(Collections.emptyList());
        mockMvc.perform(get(FIND_ALBUMS_ENDPOINT, "5464").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    private List<Album> getAlbums() {
        Album album = new Album();
        album.setCollectionId(987);
        album.setAmgArtistId(8754);
        album.setArtistId(3214);
        album.setArtistName("ABBA");
        List<Album> albums = List.of(album);
        return albums;
    }
}
