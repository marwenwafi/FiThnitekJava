/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek;

import fithnitek.controllers.UserController;
import fithnitek.models.User;
import fithnitek.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author marwe
 */
public class FiThnitek extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        UserController uc = new UserController();
        uc.afficher().forEach(System.out::println);
        User u = new User("qsd@qsd", "1stJavaUser", "first", 1235678, new Date(1995,12,10));
        u.setId(15);
        System.out.println("--------------------");
        //uc.ajouter(u);
        uc.supprimer(u);
        uc.afficher().forEach(System.out::println);
    
        /*
        Parent root = FXMLLoader.load(getClass().getResource("mainLogin.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
