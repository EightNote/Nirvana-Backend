package com.eightnote.nirvana.services;

import com.eightnote.nirvana.DAOs.TrendingDAO;
import com.eightnote.nirvana.models.Trending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrendingService {
    @Autowired
    private final TrendingDAO trendingDAO;

    public TrendingService(TrendingDAO trendingDAO) {
        this.trendingDAO = trendingDAO;
    }

    public Trending getTrending() {
        return trendingDAO.getTrending();
    }
}
