package com.app.favorite.domain.repositories;

import com.app.favorite.domain.Album;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer> {

    @Query(value = "select * from album where amg_artist_id = (select amg_artist_id from user where user_id = ?1)", nativeQuery = true)
    List<Album> findAlbums(int userId);

    @Query("select case when count(*) > 0 then true else false end from Album a where amgArtistId = :amgArtistId")
    boolean existsByAmgArtistId(@Param("amgArtistId") int amgArtistId);

}
