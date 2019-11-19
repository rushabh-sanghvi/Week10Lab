package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;

/**
 *
 * @author awarsyle
 */
public class AccountService {

    public User login(String email, String password, String path) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);

        if (user == null) {
            return null;
        }

        if (!user.getPassword().equals(password)) {
            return null;
        }

        Logger.getLogger(AccountService.class.getName())
                .log(Level.INFO, "User logged in: {0}", email);

        String to = user.getEmail();
        String subject = "Home Inventory - login";
        String template = path + "/emailtemplates/login.html";
        HashMap<String, String> tags = new HashMap<>();
        tags.put("firstname", user.getFname());
        tags.put("date", ((new Date()).toString()));

        GmailService.sendMail(to, subject, template, tags);

        return user;
    }

    public User resetPassword(String email, String path, String url) throws Exception {
        String uuid = UUID.randomUUID().toString();

        UserDB userDB = new UserDB();
        User user = null;
        try {
            user = userDB.getUser(email);
            user.setResetpassworduuid(uuid);
            userDB.update(user);
        } catch (Exception ex) {
        }
        if (user == null) {
            return null;
        }
        String link = url + "?uuid=" + uuid;
        
        String to = user.getEmail();
        String subject = "Home Inventory - Password Reset";
        String template = path + "/emailtemplates/resetpassword.html";
        HashMap<String, String> tags = new HashMap<>();
        tags.put("firstname", user.getFname());
        tags.put("email", user.getEmail());
        tags.put("link",link);
        
        GmailService.sendMail(to, subject, template, tags);

        return user;

    }

    public boolean changePassword(String uuid, String password) {
        UserService us = new UserService();
        try {
            User user = us.getByUUID(uuid);
            user.setPassword(password);
            user.setResetpassworduuid(null);
            UserDB ur = new UserDB();
            ur.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
