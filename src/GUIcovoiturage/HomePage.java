/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIcovoiturage;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


     
    import javafx.application.Application;
    import javafx.scene.Scene;
    import javafx.stage.Stage;


/**
 *
 * @author House
 */
public class HomePage extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
  
      try {    
        Parent root = FXMLLoader.load(getClass().getResource("AfficherOffreCovoiturage.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("StyleCovoiturage.css").toExternalForm());
        stage.setTitle("Offre Covoiturage");
        stage.setScene(scene);
        stage.show(); }
      catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
     
      
      
       
     /*   try {
            Parent root = FXMLLoader
        .load(getClass().getResource("login.fxml"));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Hello");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

