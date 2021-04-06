package com.app.favorite.controllers;

import com.app.favorite.dto.artist.Artist;
import com.app.favorite.services.UserFacade;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ArtistControllerTest {

    private static final String FIND_ARTISTS_ENDPOINT = "/artists/{artistName}";
    private static final String SAVE_ARTIST_ENDPOINT = "/artists//amgArtistId/{amgArtistId}/userId/{userId}";

    private UserFacade userFacade;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        userFacade = mock(UserFacade.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ArtistController(userFacade)).build();
    }

    @Test
    public void testGetArtists_returnsStatus_OK() throws Exception {
        List<Artist> artists = getArtists();
        given(userFacade.getArtists(anyString())).willReturn(artists);

        mockMvc.perform(get(FIND_ARTISTS_ENDPOINT, "1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetArtists_returnsStatus_NOT_FOUND() throws Exception {
        given(userFacade.getArtists(anyString())).willReturn(Collections.emptyList());

        mockMvc.perform(get(FIND_ARTISTS_ENDPOINT, "1").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveFavoriteArtist_returnsStatus_OK() throws Exception {
       doNothing().when(userFacade).prepareAlbumsForUser(anyInt(), anyInt());

        mockMvc.perform(post(SAVE_ARTIST_ENDPOINT, "1111", "2222").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    private List<Artist> getArtists() {
        Artist artist = new Artist();
        artist.setArtistId(1);
        artist.setAmgArtistId(12);
        artist.setArtistId(123);
        List<Artist> artists = List.of(artist);
        return artists;
    }

}
