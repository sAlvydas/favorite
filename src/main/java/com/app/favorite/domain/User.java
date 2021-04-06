package com.app.favorite.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    private long userId;

    private int amgArtistId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getAmgArtistId() {
        return amgArtistId;
    }

    public void setAmgArtistId(int amgArtistId) {
        this.amgArtistId = amgArtistId;
    }
}
