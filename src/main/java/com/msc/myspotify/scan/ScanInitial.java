package com.msc.myspotify.scan;

import com.msc.myspotify.dao.DAO;
import com.msc.myspotify.entity.Folder;
import com.msc.myspotify.entity.Musique;
import com.msc.myspotify.helper.Constantes;
import java.io.File;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class ScanInitial {
  protected static ExecutorService executor = null;

    protected static synchronized ExecutorService getExecutor() {
        if (executor == null) {
            executor = Executors.newCachedThreadPool();
        }
        return executor;
    }

    private void readFolder(File rootFolder) throws SQLException {
        UUID rootUuid = UUID.fromString(Folder.ROOT_ID);
        Folder folder = new Folder();
        folder.setId(rootUuid.toString());
        folder.setIdParent(null);
        folder.setName(rootFolder.getName());
        folder.setPathname(rootFolder.getAbsolutePath());
        folder = DAO.getFolderDAO().insert(folder);
        for (File file : rootFolder.listFiles()) {
            if (file.isDirectory()) {
                getExecutor().submit(new Thread() {
                    @Override
                    public void run() {
                        try {
                            scan(file, rootUuid, UUID.randomUUID());
                        } catch (SQLException ex) {
                            Logger.getLogger(ScanInitial.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            } else if (isGoodFile(file, Constantes.EXTENTION_MEDIA_FILE)) {
                Musique m = TagHelper.getInfo(file);
                m.setFolderId(folder.getId());
                m.setId(UUID.randomUUID().toString());
                DAO.getMusiqueDAO().insert(m);
            }
        }
    }

    private void scan(File parentFolderFile, UUID rootid, UUID id) throws SQLException {
        Folder folder = new Folder();
        folder.setId(id.toString());
        folder.setIdParent(rootid.toString());
        folder.setName(parentFolderFile.getName());
        folder.setPathname(parentFolderFile.getAbsolutePath());
        folder = DAO.getFolderDAO().insert(folder);
        for (File file : parentFolderFile.listFiles()) {
            if (file.isDirectory()) {
                getExecutor().submit(new Thread() {
                    @Override
                    public void run() {
                        try {
                            scan(file, id, UUID.randomUUID());
                        } catch (SQLException ex) {
                            Logger.getLogger(ScanInitial.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });

            } else if (isGoodFile(file, Constantes.EXTENTION_MEDIA_FILE)) {
                Musique m = TagHelper.getInfo(file);
                m.setFolderId(folder.getId());
                m.setId(UUID.randomUUID().toString());
                DAO.getMusiqueDAO().insert(m);
            } else if (isGoodFile(file, Constantes.EXTENTION_PICTURE_FILE)) {
                folder.setImgAlbum(file.getAbsolutePath());
                DAO.getFolderDAO().update(folder);
            }
        }
    }

    private boolean isGoodFile(File file, String[] exts) { //fomctionne pour l'audio et la video
        String filename = file.getName().toLowerCase();
        for (String ext : exts) {
            if (filename.endsWith(ext) && file.length() > 0) {
                return true;
            }
        }
        return false;
    }

    public void launchScan(File initialFolder) throws SQLException {
        readFolder(initialFolder);
        //DAO.lsatScanDAO.update(new BigInteger("" + System.currentTimeMillis()));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("folder obligatoire ! Merci");
            return;
        }
        DAO.init();
        new ScanInitial().launchScan(new File(args[0]));
    }
}
