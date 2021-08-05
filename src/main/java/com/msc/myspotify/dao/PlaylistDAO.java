package com.msc.myspotify.dao;

import com.msc.myspotify.entity.Playlist;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Michael
 */
public class PlaylistDAO extends AbstractDAO<Playlist> {

    public List<Playlist> getAllPlaylists() {
        return this.getObjects("from Playlist");
    }

    public boolean savePlayList(Playlist pl) {
        if (pl.getId() == null) {
            return false;
        }
        Session session = this.getSession();
        Transaction trans = session.beginTransaction();
        Playlist original = session.getReference(Playlist.class, pl.getId());
        original.setMusiques(pl.getMusiques());
        session.update(original);
        trans.commit();
        session.close();
        return true;
    }

}
