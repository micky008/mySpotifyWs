package com.msc.myspotify.controller;

import com.msc.myspotify.dao.DAO;
import com.msc.myspotify.entity.Folder;
import com.msc.myspotify.entity.Musique;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Michael
 */
@Path("musique")
public class MusiqueController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public List<Musique> getMusiqueByFolder(@PathParam("id") String folderId) {
        Folder f = DAO.getFolderDAO().getFolderById(folderId);
        return DAO.getMusiqueDAO().getMusiquesByFolder(f);
    }

}
