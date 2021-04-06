package com.app.favorite.dto.album;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AlbumsResponse {

    @JsonProperty("resultCount")
    private Integer resultCount;
    @JsonProperty("results")
    private List<AlbumDto> albumDtos = null;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<AlbumDto> getAlbums() {
        return albumDtos;
    }

    public void setAlbums(List<AlbumDto> albumDtos) {
        this.albumDtos = albumDtos;
    }
}