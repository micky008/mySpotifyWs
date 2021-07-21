package com.msc.myspotify;

import com.msc.myspotify.dao.DAO;
import com.sun.net.httpserver.HttpServer;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Michael
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DAO.init();
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        ResourceConfig config = new ResourceConfig()
                .packages(true, "com.msc.myspotify");
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config);
        System.out.println("Server started");
    }

}
