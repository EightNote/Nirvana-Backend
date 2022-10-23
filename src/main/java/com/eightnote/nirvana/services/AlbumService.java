package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.AlbumDAO;
import com.eightnote.nirvana.models.Album;
import com.eightnote.nirvana.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AlbumService {
    @Autowired
    private final AlbumDAO albumDAO;

    public AlbumService(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    public int toggleLike(String username, String albumName) {
        albumDAO.toggleLike(username , albumName, isLikedBy(username, albumName));
        return albumDAO.getAlbum(albumName).getLikes();
    }
    public Album getAlbum(String albumName) {
        return albumDAO.getAlbum(albumName);
    }

    public List<Country> getReleasedCountries(String album) {
        return albumDAO.getReleaseCountries(album);
    }

    public void createAlbum(String albumName, String albumLogo, String artistId, int genreId) {
        albumDAO.createAlbum(albumName, albumLogo, artistId, genreId);
    }

    public int getLikeCount(String albumName) {
        return albumDAO.getLikes(albumName).size();
    }

    public List<String> getLikes(String albumName) {
        return albumDAO.getLikes(albumName);
    }

    public boolean isLikedBy(String username, String albumName) {
        return albumDAO.getLikes(albumName).contains(username);
    }

    public boolean isReleasedInCountry(String albumName, String countryName) {
        var released_countries = albumDAO.getReleaseCountries(albumName);
        for (var c : released_countries) {
            if (c.getName().equals(countryName)) return true;
        }
        return false;
    }
}
