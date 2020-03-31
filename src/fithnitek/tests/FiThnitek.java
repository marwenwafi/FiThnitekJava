/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.tests;

import fithnitek.controllers.UserCallbackHandler;
import fithnitek.controllers.UserController;
import fithnitek.models.User;
import fithnitek.utils.BCryptPasswordEncoder;
import fithnitek.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 *
 * @author marwe
 */
public class FiThnitek extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        UserController uc = new UserController();
        //uc.afficher().forEach(System.out::println);
        //User u = new User("qsd@qsd", "1stJavaUser", "first","$2y$15$ipOy0E4xxbWtfJYMOqpGpel6D.OxG8pvzIS7eyTNCMCuEVHCB..Nm", 1235678, new Date(1995,12,10));
        //u.setId(15);
        //System.out.println("--------------------");
        //uc.ajouter(u);
        //uc.supprimer(u);
        //uc.afficher().forEach(System.out::println);
        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("Login:");
        String l = sc.next();
        System.out.println("Pass:");
        String p = sc.next();
        uc.attemptLogin(l, p);
        
        */
        /*
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        System.out.println("BCrypt test -------------->");
        System.out.println(b.checkPassword("12345", "$2y$13$A16w.3LX8P3/YBjnN4LE3O/2MjX0HK9pGKKJzRU5da4Pq8mcukoSK"));
        System.out.println(b.checkPassword("12345", "$2y$15$ipOy0E4xxbWtfJYMOqpGpel6D.OxG8pvzIS7eyTNCMCuEVHCB..Nm"));
        System.out.println(b.hashPassword("12345"));
        */
        
        Parent root = FXMLLoader.load(getClass().getResource("/fithnitek/views/mainLogin.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
