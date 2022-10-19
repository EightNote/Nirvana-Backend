package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.GenreDAO;
import com.eightnote.nirvana.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenreService {

    @Autowired
    private final GenreDAO genreDAO;

    public GenreService(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

//    public Genre getGenre(String trackName) {
//        return genreDAO.getGenre(trackName);
//    }
}
