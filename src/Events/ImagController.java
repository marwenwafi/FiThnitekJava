/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sourour
 */
public class ImagController implements Initializable {

    @FXML
    public static ImageView img;
    String path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        path=EventQuController.Verifimage;
       /* try {
            System.out.println("path============================="+path);
            Image image = new Image(new FileInputStream("C:\\Users/sourour/Documents/s.jpg"));
            img.setImage(image);
            img.setFitHeight(235);
            img.setFitWidth(431);
            img.setX(75);
            img.setY(20);
            
        } catch (FileNotFoundException ex) {
            System.out.println("no image ");
        }*/
    }    

    @FXML
    private void ok(ActionEvent event) {
         final Node source = (Node) event.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    }
    
    
}
