package com.msc.myspotify.controller;

import java.net.http.HttpRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author Michael
 */
@Path("user")
public class UserController {

    @Context
    private HttpRequest httpHeaders;

    @GET
    public Response getUser(){
        return Response.noContent().build();
    }
    
}
