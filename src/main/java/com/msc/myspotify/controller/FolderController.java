package com.msc.myspotify.controller;

import com.msc.myspotify.dao.DAO;
import com.msc.myspotify.entity.Folder;
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
@Path("folder")
public class FolderController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Folder> getAllFolders() {
        return DAO.getFolderDAO().getRootFolders();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Folder> getInternFolder(@PathParam("id") String idFolder) {
       return DAO.getFolderDAO().getChildFolders(idFolder);
    }
    
    

}
