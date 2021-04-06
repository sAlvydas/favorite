package com.app.favorite.domain;

import javax.persistence.*;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private int collectionId;

    private int amgArtistId;

    private int artistId;

    private String artistName;

    public Album(int collectionId, int amgArtistId, int artistId, String artistName) {
        this.collectionId = collectionId;
        this.amgArtistId = amgArtistId;
        this.artistId = artistId;
        this.artistName = artistName;
    }

    public Album() {
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public int getAmgArtistId() {
        return amgArtistId;
    }

    public void setAmgArtistId(int amgArtistId) {
        this.amgArtistId = amgArtistId;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
