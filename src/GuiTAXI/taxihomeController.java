/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiTAXI;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author Rawis
 */
public class taxihomeController implements Initializable {
    
 @FXML
 private ImageView imageclient;
 @FXML
 private ImageView imagedriver;
 
 public  void client( ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("espaceclient.fxml"));
        Scene scene = new Scene(root);
     scene.getStylesheets().add(getClass().getResource("styletaxi.css").toExternalForm());
          Stage newWindow = new Stage();
           newWindow.setTitle(" Taxi Request ");
           newWindow.setScene(scene);
           newWindow.show();
    }

   public  void taxi(ActionEvent  event) throws IOException {
      Parent root1 = FXMLLoader.load(getClass().getResource("espacechauffeur.fxml"));
        Scene scene = new Scene(root1);
     scene.getStylesheets().add(getClass().getResource("styletaxi.css").toExternalForm());
          Stage newWindow = new Stage();
           newWindow.setTitle("  Taxi Driver ");
           newWindow.setScene(scene);
           newWindow.show();
    }
   
   
/*
    void taxi() throws IOException {
      Parent root1 = FXMLLoader.load(getClass().getResource("espacechauffeur.fxml"));
        Scene scene = new Scene(root1);
     scene.getStylesheets().add(getClass().getResource("styletaxi.css").toExternalForm());
          Stage newWindow = new Stage();
           newWindow.setTitle(" Menu  ");
           newWindow.setScene(scene);
           newWindow.show();
    }
    
    
 
    void client() throws IOException {
     Parent root1 = FXMLLoader.load(getClass().getResource("espaceclient.fxml"));
        Scene scene = new Scene(root1);
     scene.getStylesheets().add(getClass().getResource("styletaxi.css").toExternalForm());
          Stage newWindow = new Stage();
           newWindow.setTitle(" Menu  ");
           newWindow.setScene(scene);
           newWindow.show();
    } */
     public  void menu(ActionEvent event) throws IOException {
      Parent root1 = FXMLLoader.load(getClass().getResource("/fithnitek/views/mainMenu.fxml"));
        Scene scene = new Scene(root1);
     scene.getStylesheets().add(getClass().getResource("styletaxi.css").toExternalForm());
          Stage newWindow = new Stage();
           newWindow.setTitle(" Menu  ");
           newWindow.setScene(scene);
           newWindow.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
       
      Image img =  new Image("images/client.png");
      imageclient.setImage(img);
       Image img01 =  new Image("images/siwar.png");
      imagedriver.setImage(img01);
    }
   
    
    
}
  
    
    
    

 





