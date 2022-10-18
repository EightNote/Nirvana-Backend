package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.AlbumDAO;
import com.eightnote.nirvana.models.Album;
import com.eightnote.nirvana.models.Country;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AlbumService {
    @Autowired
    private final AlbumDAO albumDAO;

    public AlbumService(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    public int like(String albumName) {
        albumDAO.like(albumName);
        return albumDAO.getAlbum(albumName).getLikes();
    }
    public Album getAlbum(String albumName) {
        return albumDAO.getAlbum(albumName);
    }

    public List<Country> getReleasedCountries(String album) {
        return albumDAO.getReleaseCountries(album);
    }

    public void createAlbum(String albumName) {
        albumDAO.createAlbum(albumName);
    }
}
