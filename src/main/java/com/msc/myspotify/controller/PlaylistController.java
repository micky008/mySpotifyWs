package com.msc.myspotify.controller;

import com.msc.myspotify.dao.DAO;
import com.msc.myspotify.entity.Playlist;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Michael
 */
@Path("playlist")
public class PlaylistController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Playlist> getAllPlaylist() {
        return DAO.getPlaylistDAO().getAllPlaylists();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Boolean savePlayList(Playlist pl) {
        if (pl.getId() == null){
           return false;
        }
        DAO.getPlaylistDAO().savePlayList(pl);
        return true;
    } 
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Playlist addNewPlayList(Playlist pl) {
        pl.setId(UUID.randomUUID().toString());
       return DAO.getPlaylistDAO().insert(pl);
    }
    
    

}
