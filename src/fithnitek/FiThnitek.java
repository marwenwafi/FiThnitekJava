/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek;

import fithnitek.controllers.Covoiturage;
import fithnitek.controllers.ReservationCovoiturageService;
import fithnitek.controllers.UserController;
import fithnitek.models.OffreCovoiturage;
import fithnitek.models.ReservationCovoiturage;
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
       Covoiturage sp = new Covoiturage();
        OffreCovoiturage o = new OffreCovoiturage(5, "Tunisia","Paris","24-25-2020",5,10,"Bmw");
        //sp.ajouteroffrecovoiturage(o);
        //sp.supprimeroffrecovoiturage(151);
        //sp.modifieroffrecovoiturage(148,5,"Tunis","Corona","25-25-2020",5,22,"Bmw");
         System.out.println("-------Tous les covoiturages----------");        
        sp.afficheroffrecovoiturageback().forEach(System.out::println);
        System.out.println("--------Afficher covoiturage Front---------");
        sp.afficherallcovoiturage(4).forEach(System.out::println);
        System.out.println("-------Afficher les covoiturage de l'utilisateur---------");
        sp.affichercovoiturageutilisateur(5).forEach(System.out::println);
        System.out.println("--------Recherche---------");
        sp. rechercheoffre(4,2,"2025-25-25","c","t").forEach(System.out::println);
        System.out.println("--------Ordonner---------");
        sp.Ordonneroffre(4).forEach(System.out::println);
        System.out.println("--------Ordonner2---------");
        sp.Ordonneroffre2(4).forEach(System.out::println);
        System.out.println("--------Reservation---------"); 
        ReservationCovoiturage r = new ReservationCovoiturage(5,127,2,10);
        ReservationCovoiturageService sr = new ReservationCovoiturageService() ; 
        
       // sr.ajouterrerservationocovoiturage(r);
       //sr.afficherreservationcovoiturageutilisateur(5).forEach(System.out::println);
      // sr.supprimerreservationcovoiturage(49);
       sr.afficherreservationcovoiturageutilisateur(5).forEach(System.out::println);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
