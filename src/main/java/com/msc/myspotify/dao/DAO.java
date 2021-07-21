package com.msc.myspotify.dao;

/**
 *
 * @author Michael
 */
public class DAO {

    private static UserDAO udao = new UserDAO();
    private static MusiqueDAO mdao = new MusiqueDAO();
    private static FolderDAO fdao = new FolderDAO();
    private static PlaylistDAO pldao = new PlaylistDAO();

    public static void init() {
        HibernateFactory.setUp();
    }

    public static void close() {
    }

    public static UserDAO getUserDAO() {
        return udao;
    }

    public static MusiqueDAO getMusiqueDAO() {
        return mdao;
    }

    public static FolderDAO getFolderDAO() {
        return fdao;
    }

    public static PlaylistDAO getPlaylistDAO() {
        return pldao;
    }

}
