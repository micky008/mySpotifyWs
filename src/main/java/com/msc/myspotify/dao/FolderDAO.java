package com.msc.myspotify.dao;

import com.msc.myspotify.entity.Folder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Michael
 */
public class FolderDAO extends AbstractDAO<Folder> {
    
    public List<Folder> getChildFolders(String id) {
        List<Folder> lf = this.getObjects("from Folder where idParent='" + id + "'");
        Collections.sort(lf, new MySort());
        return lf;
    }
    
    public List<Folder> getRootFolders() {
        List<Folder> lf = this.getObjects("from Folder where idParent='" + Folder.ROOT_ID + "'");
        Collections.sort(lf, new MySort());
        for (Folder f : lf) {
            f.setPathname(null);
        }
        return lf;
    }
    
    public Folder getFolderById(String id) {
        return this.getObject("from Folder where id='" + id + "'");
    }
    
    private class MySort implements Comparator<Folder> {
        
        @Override
        public int compare(Folder o1, Folder o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    
}
