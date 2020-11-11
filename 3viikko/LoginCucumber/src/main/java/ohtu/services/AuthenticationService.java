package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        if (username.length() < 4) {
            return true;
        }
        if (password.length() < 9) {
            return true;
        }
        
        if (passwordIsInvalid(password)) {
            return true;
        }
        return false;
    }
    
    private boolean passwordIsInvalid(String password) {
        boolean merkki = true;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (erikoismerkki(c)) {
                merkki = false;
            }
        }
        return merkki;
    }
    
    private boolean erikoismerkki(char c) {
        if (c < 65) {
            return true;
        } else if (c > 90 && c < 97) {
            return true;
        } else if (c > 122) {
            return true;
        }
        return false;
    }
}
