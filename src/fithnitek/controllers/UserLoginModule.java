/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import com.sun.security.auth.UserPrincipal;
import java.io.IOException;
import java.util.Map;

import fithnitek.models.User;
import fithnitek.utils.BCryptPasswordEncoder;
import java.util.prefs.Preferences;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 *
 * @author marwe
 */
public class UserLoginModule {
    
    private String user;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;
    private UserPrincipal userPrincipal;
    private String username;

    private boolean succeeded = false;

    public UserLoginModule() {
            System.out.println("Login Module - constructor called");
    }

    public boolean abort() throws LoginException {
            System.out.println("Login Module - abort called");
            return false;
    }

    public boolean commit() throws LoginException {
            System.out.println("Login Module - commit called");
            Preferences userPreferences = Preferences.userRoot();
            userPreferences.put("User",username);
            return succeeded;
    }

    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
                    Map<String, ?> options) {

            System.out.println("Login Module - initialize called");
            this.callbackHandler = callbackHandler;
            this.sharedState = sharedState;
            this.options = options;

            System.out.println("testOption value: " + (String) options.get("testOption"));

            succeeded = false;
    }

    public boolean login() throws LoginException {
            System.out.println("Login Module - login called");
            if (callbackHandler == null) {
                    throw new LoginException("Oops, callbackHandler is null");
            }

            Callback[] callbacks = new Callback[2];
            callbacks[0] = new NameCallback("name:");
            callbacks[1] = new PasswordCallback("password:", false);

            try {
                    callbackHandler.handle(callbacks);
            } catch (IOException e) {
                    throw new LoginException("Oops, IOException calling handle on callbackHandler");
            } catch (UnsupportedCallbackException e) {
                    throw new LoginException("Oops, UnsupportedCallbackException calling handle on callbackHandler");
            }

            NameCallback nameCallback = (NameCallback) callbacks[0];
            PasswordCallback passwordCallback = (PasswordCallback) callbacks[1];

            String name = nameCallback.getName();
            this.username = name;
            String password = new String(passwordCallback.getPassword());

            UserController uc = new UserController();
            User u = uc.oneUser(name);
            this.user = name;
            BCryptPasswordEncoder b = new BCryptPasswordEncoder();
            
            if (u.getUsername().equals(name) && b.checkPassword(password,u.getHashedPwd())) {
                    System.out.println("Success! You get to log in!");
                    succeeded = true;
                    return succeeded;
            } else {
                    System.out.println("Failure! You don't get to log in");
                    succeeded = false;
                    throw new FailedLoginException("Sorry! No login for you.");
            }
    }

    public boolean logout() throws LoginException {
            System.out.println("Login Module - logout called");
            return false;
    }
}
