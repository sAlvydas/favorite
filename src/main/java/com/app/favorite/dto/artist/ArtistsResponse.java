
package com.app.favorite.dto.artist;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

public class ArtistsResponse {

    @JsonProperty("resultCount")
    private Integer resultCount;
    @JsonProperty("results")
    private List<Artist> artists = null;


    public Integer getResultCount() {
        return resultCount;
    }


    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }


    public List<Artist> getArtists() {
        return artists;
    }


    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

}

