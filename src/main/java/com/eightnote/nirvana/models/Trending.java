package com.eightnote.nirvana.models;

import java.util.List;

public class Trending {
    List<Album> trendingAlbums;
    List<Track> trendingTracks;
    List<ArtistDetails> trendingArtist;

    public List<Album> getTrendingAlbums() {
        return trendingAlbums;
    }

    public void setTrendingAlbums(List<Album> trendingAlbums) {
        this.trendingAlbums = trendingAlbums;
    }

    public List<Track> getTrendingTracks() {
        return trendingTracks;
    }

    public void setTrendingTracks(List<Track> trendingTracks) {
        this.trendingTracks = trendingTracks;
    }

    public List<ArtistDetails> getTrendingArtist() {
        return trendingArtist;
    }

    public void setTrendingArtist(List<ArtistDetails> trendingArtist) {
        this.trendingArtist = trendingArtist;
    }
}
