package com.msc.myspotify.dao;

import com.msc.myspotify.entity.Folder;
import com.msc.myspotify.entity.Musique;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Michael
 */
public class MusiqueDAO extends AbstractDAO<Musique> {

    public Musique getMusiqueById(String idMusique) {
        return this.getObject("from Musique where id='" + idMusique + "'");
    }

//    public List<Musique> getMusiquesByPlayer(int playerId) {
//        return this.getObjects("select pmp.musique from PlayerMusiquePlaceDAO pmp where player =" + playerId);
//    }

    public List<Musique> getMusiquesByFolder(Folder folder) {
        List<Musique> lm = this.getObjects("from Musique where folderId='" + folder.getId() + "'");
        Collections.sort(lm, new Comparator<Musique>() {
            @Override
            public int compare(Musique o1, Musique o2) {
                return o1.getTitre().compareTo(o2.getTitre());
            }
        });
        return lm;
    }

}
