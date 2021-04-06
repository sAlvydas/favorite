package com.app.favorite.services;

import com.app.favorite.clients.ItunesClient;
import com.app.favorite.domain.Album;
import com.app.favorite.domain.User;
import com.app.favorite.domain.repositories.AlbumRepository;
import com.app.favorite.domain.repositories.UserRepository;
import com.app.favorite.dto.album.AlbumDto;
import com.app.favorite.mapper.AlbumMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserFacadeTest {

    private AlbumService albumService;

    private ArtistService artistService;

    private AlbumMapper albumMapper;

    private UserFacade userFacade;

    private ItunesClient itunesClient;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setup() {
        itunesClient = mock(ItunesClient.class);
        albumService = new AlbumService(albumRepository);
        artistService = new ArtistService(itunesClient, userRepository);
        userFacade = new UserFacade(albumService, artistService, new AlbumMapper(), itunesClient);
    }

    @Test
    public void testPrepareAlbumsForUser_UserAndAlbumsSaved() {
        AlbumDto albumDto = getAlbumDto();
        when(itunesClient.getAlbums(444)).thenReturn(List.of(albumDto));
        userFacade.prepareAlbumsForUser(444, 17);

        User user = userRepository.findById((long) 17).get();
        assertNotNull(user);
        assertEquals(444, user.getAmgArtistId());
        assertEquals(17, user.getUserId());

        List<Album> albums = albumRepository.findAlbums(17);
        assertTrue(!CollectionUtils.isEmpty(albums));
        Album album = albums.get(0);
        assertEquals(1234, album.getCollectionId());
        assertEquals(444, album.getAmgArtistId());
        assertEquals(666, album.getArtistId());
        assertEquals("BIPLAN", album.getArtistName());
    }

    private AlbumDto getAlbumDto() {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setCollectionId(1234);
        albumDto.setAmgArtistId(789);
        albumDto.setArtistName("BIPLAN");
        albumDto.setAmgArtistId(444);
        albumDto.setArtistId(666);
        return albumDto;
    }

}
