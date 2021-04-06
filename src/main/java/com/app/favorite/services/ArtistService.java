package com.app.favorite.services;

import com.app.favorite.clients.ItunesClient;
import com.app.favorite.domain.User;
import com.app.favorite.domain.repositories.UserRepository;
import com.app.favorite.dto.artist.Artist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ItunesClient itunesClient;

    private final UserRepository userRepository;

    public ArtistService(ItunesClient itunesClient, UserRepository userRepository) {
        this.itunesClient = itunesClient;
        this.userRepository = userRepository;
    }

    public List<Artist> getArtists(String artistName) {
        return itunesClient.getArtistsByName(artistName);
    }

    public void saveArtist(int amgArtistId, long userId) {
        User user = new User();
        user.setUserId(userId);
        user.setAmgArtistId(amgArtistId);
        userRepository.save(user);
    }
}
