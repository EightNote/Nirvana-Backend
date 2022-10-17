package com.eightnote.nirvana.row_mappers;

import com.eightnote.nirvana.models.ArtistPhotos;
import org.springframework.jdbc.core.RowMapper;

public class ArtistPhotosRowMapper {
     public static RowMapper<ArtistPhotos> imageLinksRowMapper = (rs, rowNum) ->
             new ArtistPhotos(
                    rs.getInt("srno"),
                    rs.getString("link"),
                            rs.getString("decc")
                            );
}
