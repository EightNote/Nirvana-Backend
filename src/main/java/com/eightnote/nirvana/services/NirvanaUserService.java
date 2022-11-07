package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.NirvanaUserDAO;
import com.eightnote.nirvana.models.ArtistAccountDetails;
import com.eightnote.nirvana.models.ArtistDetails;
import com.eightnote.nirvana.models.NirvanaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NirvanaUserService implements UserDetailsService {
    @Autowired
    private final NirvanaUserDAO nirvanaUserDAO;

    public NirvanaUserService(NirvanaUserDAO nirvanaUserDAO) {
        this.nirvanaUserDAO = nirvanaUserDAO;
    }

    public void createUser(NirvanaUser user) {
        nirvanaUserDAO.createUser(user);
    }

    public void removeUser(NirvanaUser user) {
        nirvanaUserDAO.removeUser(user);
    }

    @Override
    public NirvanaUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return nirvanaUserDAO.getUser(username);
    }

    public void getArtistDetail(NirvanaUser user) { nirvanaUserDAO.getArtistDetail(user); }

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
