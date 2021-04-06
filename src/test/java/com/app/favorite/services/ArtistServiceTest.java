package com.app.favorite.services;

import com.app.favorite.clients.ItunesClient;
import com.app.favorite.domain.User;
import com.app.favorite.domain.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistServiceTest {

    private ItunesClient itunesClient;

    private ArtistService artistService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {
        itunesClient = mock(ItunesClient.class);
        artistService = new ArtistService(itunesClient, userRepository);
    }

    @Test
    public void testSaveArtist_artistSaved() {
        artistService.saveArtist(123, 321);
        Optional<User> byId = userRepository.findById((long) 321);
        User user = byId.get();
        assertEquals(123, user.getAmgArtistId());
        assertEquals(321, user.getUserId());
    }
}
