package com.eightnote.nirvana.DAOs;

import com.eightnote.nirvana.models.Album;
import com.eightnote.nirvana.models.ArtistDetails;
import com.eightnote.nirvana.models.Track;
import com.eightnote.nirvana.models.Trending;
import com.eightnote.nirvana.row_mappers.AlbumRowMapper;
import com.eightnote.nirvana.row_mappers.ArtistDetailsRowMapper;
import com.eightnote.nirvana.row_mappers.TrackRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrendingDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public TrendingDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Trending getTrending() {
        Trending trending = new Trending();
        String albumSql = "SELECT Album.*, COUNT(liked_by_id) as like_count FROM Album LEFT OUTER JOIN AlbumLikes ON Album.id = album_id GROUP BY Album.id ORDER BY like_count;";
        List<Album> trendingAlbums = jdbcTemplate.query(albumSql, AlbumRowMapper.albumRowMapper);

        String artistSql = "SELECT Artist.*, COUNT(followed_by_id) as follower_count FROM Artist LEFT OUTER JOIN Followers ON Artist.username = artist_id GROUP BY Artist.username ORDER BY follower_count;";
        List<ArtistDetails> trendingArtist = jdbcTemplate.query(artistSql, ArtistDetailsRowMapper.artistDetailsRowMapper);

        String trackSql = "SELECT Track.*, COUNT(liked_by_id) as like_count FROM Track LEFT OUTER JOIN TrackLikes ON Track.title = track_id GROUP BY Track.title ORDER BY like_count;";
        List<Track> trendingTrack = jdbcTemplate.query(trackSql, TrackRowMapper.trackRowMapper);

        trending.setTrendingAlbums(trendingAlbums);
        trending.setTrendingArtist(trendingArtist);
        trending.setTrendingTracks(trendingTrack);

        return trending;
    }
}
