/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import java.net.URL;
import java.security.AccessController;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.security.auth.Subject;

public class MainMenuController implements Initializable {

    
    @FXML
    private Label welcomename;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Preferences userPreferences = Preferences.userRoot();
        String username = userPreferences.get("User","No User");
        welcomename.setText(welcomename.getText()+" "+username);
    }    
    
}
