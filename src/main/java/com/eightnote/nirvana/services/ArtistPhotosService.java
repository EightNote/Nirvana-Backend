package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.ArtistPhotosDAO;
import com.eightnote.nirvana.models.ArtistPhotos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistPhotosService {
    @Autowired
    private final ArtistPhotosDAO artistPhotosDAO;

    public ArtistPhotosService(ArtistPhotosDAO artistPhotosDAO) {
        this.artistPhotosDAO = artistPhotosDAO;
    }

    public List<ArtistPhotos> imageUrls(String artistName) {
        return artistPhotosDAO.getImages(artistName);
    }
}
