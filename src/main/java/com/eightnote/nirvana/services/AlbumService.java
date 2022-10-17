package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.AlbumDAO;
import com.eightnote.nirvana.models.Album;
import org.springframework.beans.factory.annotation.Autowired;

public class AlbumService {
    @Autowired
    private final AlbumDAO albumDAO;

    public AlbumService(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    public Album getAlbum(String albumName) {
        return albumDAO.getAlbum(albumName);
    }

    public void createAlbum(String albumName) {
        albumDAO.createAlbum(albumName);
    }
}
