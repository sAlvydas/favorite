package com.app.favorite.clients;

import com.app.favorite.dto.album.AlbumDto;
import com.app.favorite.dto.album.AlbumsResponse;
import com.app.favorite.dto.artist.Artist;
import com.app.favorite.dto.artist.ArtistsResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItunesClientTest {

    private RestTemplate restTemplate;

    private ItunesClient itunesClient;

    @Value("${itunes.url}")
    private String itunesUrl;

    @Before
    public void setup() {
        restTemplate = mock(RestTemplate.class);
        itunesClient = new ItunesClient(restTemplate, itunesUrl);
    }

    @Test
    public void testGetAlbums_returnsAlbums() {
        AlbumsResponse response = getAlbumsResponse();

        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.<URI>any(), eq(AlbumsResponse.class))).thenReturn(responseEntity);
        List<AlbumDto> albums = itunesClient.getAlbums(23541);
        AlbumDto actualAlbumDto = albums.get(0);

        assertEquals("ABBA",actualAlbumDto.getArtistName());
        assertEquals(Integer.valueOf(123), actualAlbumDto.getAmgArtistId());
        assertEquals(Integer.valueOf(321), actualAlbumDto.getArtistId());
    }

    @Test
    public void testGetArtistsByName_returnsArtistResponse() {
        ArtistsResponse response = getArtistsResponse();

        ResponseEntity<ArtistsResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        when(restTemplate.getForEntity(Mockito.<URI>any(), eq(ArtistsResponse.class))).thenReturn(responseEntity);
        List<Artist> artists = itunesClient.getArtistsByName("ABBA");
        Artist actualArtist = artists.get(0);
        assertEquals(Integer.valueOf(123), actualArtist.getArtistId());
        assertEquals("ABBA", actualArtist.getArtistName());
    }

    @Test
    public void testGetArtistsByName_throwsRestClientException_returnsEmptyList() {
        when(restTemplate.getForEntity(Mockito.<URI>any(), eq(ArtistsResponse.class))).thenThrow(RestClientException.class);
        List<Artist> artists = itunesClient.getArtistsByName("ABBA");
        assertTrue(CollectionUtils.isEmpty(artists));
    }

    private ArtistsResponse getArtistsResponse() {
        ArtistsResponse response = new ArtistsResponse();
        Artist artist = new Artist();
        artist.setArtistId(123);
        artist.setArtistName("ABBA");
        response.setArtists(List.of(artist));
        return response;
    }


    private AlbumsResponse getAlbumsResponse() {
        AlbumsResponse response = new AlbumsResponse();
        AlbumDto albumDto = new AlbumDto();
        albumDto.setAmgArtistId(123);
        albumDto.setArtistId(321);
        albumDto.setArtistName("ABBA");
        response.setAlbums(List.of(albumDto));
        return response;
    }
}
