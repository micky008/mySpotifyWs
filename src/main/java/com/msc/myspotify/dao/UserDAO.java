package com.msc.myspotify.dao;

import com.msc.myspotify.entity.User;
import com.msc.myspotify.helper.PasswordHelper;

/**
 *
 * @author Michael
 */
public class UserDAO extends AbstractDAO<User> {

    public boolean isUserExist(String login, String mdpClair) {
        String mdpEncryp = PasswordHelper.convertPassword(mdpClair);
        User u = this.getSession().find(User.class, login);
        if (u == null) {
            return false;
        }
        return u.getPwd().equals(mdpEncryp);
    }

    

}
