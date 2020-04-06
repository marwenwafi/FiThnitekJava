/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek;

import fithnitek.controllers.ServiceColis;
import fithnitek.controllers.ServiceReservationColis;
import fithnitek.controllers.UserController;
import fithnitek.models.Offre_Colis;
import fithnitek.models.Reservation_Colis;
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
        /* UserController uc = new UserController();
        uc.afficher().forEach(System.out::println);
        User u = new User("qsd@qsd", "1stJavaUser", "first", 1235678, new Date(1995,12,10));
        u.setId(15);
        System.out.println("--------------------");
        //uc.ajouter(u);
        uc.supprimer(u);
        uc.afficher().forEach(System.out::println);
     */
                // Parent root = FXMLLoader.load(getClass().getResource("/Views/AjouterOffreColisAdmin.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/ViewsColis/AjouterOffre.fxml"));
        Scene scene = new Scene(root);
    //    scene.getStylesheets().add(getClass().getResource("/Views/afficherallcolis.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

         //public Offre_Colis(int id_OffreCol, String Date_col, String Lieu_Depart, String Lieu_Arrive, int Prix, double HauteurCol, double LargeurCol, String Voiture, int idU, double LongueurCol) {
       //OC.ajouterOffreColis(new Offre_Colis("24-05-2020","Tunis3","menzah2",1,5,1.5,"mazda",10,1.6));
       //OC.afficherAllColis().forEach(System.out::println);
       //    OC.afficherOffreColis(9).forEach(System.out::println);

   //    OC.supprimerOffreColis(new Offre_Colis(137,"23-05-2020","Tunis","menzah2",1,1.6,1.5,"mazda",10,1.6));
     //  OC.afficherAllColis().forEach(System.out::println);

    //    OC.modifierOffreColis(new Offre_Colis(136,"23-05-2020","ooooooooooo","oooooooooo",1,1.6,1.5,"ooooooooo",10,1.6));
               //      OC.afficherAllColis().forEach(System.out::println);

     //  OC.RechercheOffreColis("23-05-2020").forEach(System.out::println);
    //   OC.RechercheOffreColisDepartArrive("aa","aa",10).forEach(System.out::println);
        //double HauteurResv, double LargeurResv,  int id, int id_Offre, double Longueur
      //   SR.ajouterOffreReservation(new Reservation_Colis(0.6,5,10,134,0.2));
               //     OC.afficherAllColis().forEach(System.out::println);

  //SR.afficherReservationColisAll().forEach(System.out::println);
         //  public Reservation_Colis(int id_ReservationCol, double HauteurResv, double LargeurResv, double PrixResv, int id, int id_Offre, double LongueurResv) {
     //  SR.afficherReservationColis().forEach(System.out::println);

    // SR.supprimerOffreReservation(226);
    // SR.afficherReservationColisAll().forEach(System.out::println);

   //  SR.modifierOffreReservation(new Reservation_Colis(228,6,6,6,10,84,2));
     //SR.afficherReservationColisAll().forEach(System.out::println);
     //  OC.RechercheOffreColisPrix(50,10).forEach(System.out::println);
     //    OC.TrierOffreColis().forEach(System.out::println);


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
