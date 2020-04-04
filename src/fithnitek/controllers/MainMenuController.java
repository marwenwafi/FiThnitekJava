/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import java.net.URL;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Principal;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class MainMenuController implements Initializable {

    
    @FXML
    private Label welcomename;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LoginContext l = new LoginContext("User");
            Subject current = l.getSubject();
            Subject c = Subject.getSubject(AccessController.getContext());
            Principal p = (Principal) current.getPrincipals();

            System.out.println(l.toString());
            System.out.println("---------");
            System.out.println(p.toString());
            System.out.println("---------");
            System.out.println(c.toString());
            System.out.println("---------");
            System.out.println(current.toString());
    //        welcomename.setText(welcomename.getText()+" "+current.toString());
        } catch (LoginException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
}
