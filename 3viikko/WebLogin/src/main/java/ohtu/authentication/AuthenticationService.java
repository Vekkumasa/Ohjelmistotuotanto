package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

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

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();
        
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (username.length()<3 ) {
            status.addError("username should have at least 3 characters");
        }
        
        if (password.length() < 8) {
            status.addError("password should have at least 8 characters");
        }
        
        if (!password.equals(passwordConfirmation)) {
            status.addError("password and password confirmation do not match");
        }
        
        if (passwordIsInvalid(password)) {
            status.addError("password must contain at least one symbol or number");
        }

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }
        
        return status;
    }

}
