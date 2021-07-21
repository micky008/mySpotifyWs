package com.msc.myspotify.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Michael
 */
@Entity
@Table(name = "Playlists")
public class Playlist {

    @Id
    private String id;

    private String name;
    
    @ManyToMany
    private List<Musique> playlist;

    public List<Musique> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Musique> playlist) {
        this.playlist = playlist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

}
