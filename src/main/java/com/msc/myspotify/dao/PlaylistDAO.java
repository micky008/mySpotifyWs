package com.msc.myspotify.dao;

import com.msc.myspotify.entity.Playlist;
import java.util.List;

/**
 *
 * @author Michael
 */
public class PlaylistDAO extends AbstractDAO<Playlist> {

    public List<Playlist> getAllPlaylists() {
        return this.getObjects("from Playlist");
    }

}
