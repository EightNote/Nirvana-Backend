package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.NirvanaUserDAO;
import com.eightnote.nirvana.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NirvanaUserService implements UserDetailsService {
    @Autowired
    private final NirvanaUserDAO nirvanaUserDAO;

    @Autowired
    private final GenreService genreService;

    public NirvanaUserService(NirvanaUserDAO nirvanaUserDAO, GenreService genreService) {
        this.nirvanaUserDAO = nirvanaUserDAO;
        this.genreService = genreService;
    }

    public void createUser(UserAccountDetails user) {
        nirvanaUserDAO.createUser(user);
    }

    public void removeUser(NirvanaUser user) {
        nirvanaUserDAO.removeUser(user);
    }

    @Override
    public NirvanaUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return nirvanaUserDAO.getUser(username);
    }

    public ArtistDetails getArtistDetail(NirvanaUser user) {
        return nirvanaUserDAO.getArtistDetail(user);
    }

    public UserDetails getUserDetail(NirvanaUser user) {
        var userDetails = nirvanaUserDAO.getUserDetail(user);

        var interests = new ArrayList<Genre>();

        userDetails.setInterests(genreService.allGenres());

        for (Genre g : userDetails.getInterests()) {
            g.setTrack_count(genreService.getTracks(g.getName()).size());
        }

        return userDetails;
    }

    public void createArtistAccount(ArtistAccountDetails encryptedUser) {
        nirvanaUserDAO.createArtistAccount(encryptedUser);
    }

    public List<ArtistDetails> getAllArtist(){
        return nirvanaUserDAO.getAllArtist();
    }

    public List<ArtistDetails> likedArtists(String username){
        return nirvanaUserDAO.likedArtists(username);
    }
}
