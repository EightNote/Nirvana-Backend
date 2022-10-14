package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.CountryDAO;
import com.eightnote.nirvana.models.Country;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CountryService {
    private final CountryDAO countryDAO;

    @Autowired
    public CountryService(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    public boolean addCountry(String country) {
        countryDAO.addCountry(country);
        return true;
    }

    public List<Country> getAll() {
        return countryDAO.getAllCountries();
    }
}
