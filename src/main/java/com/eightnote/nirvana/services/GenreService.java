package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.GenreDAO;
import com.eightnote.nirvana.models.Genre;
import com.eightnote.nirvana.models.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreService {

    @Autowired
    private final GenreDAO genreDAO;

    public GenreService(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    public List<Track> getTracks(String genre) {
        return genreDAO.getTracks(genre);
    }

    public void createGenre(String genreName) {
        genreDAO.createGenre(genreName);
    }

    public Genre getGenre(String genreName) {
        return genreDAO.getGenre(genreName);
    }
}
