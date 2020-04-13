/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiTAXI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Rawis
 */
public class MenuuserController implements Initializable {

    
      @FXML
    private ImageView maha;

    @FXML
    private ImageView siwar;

    @FXML
    private ImageView yassin;

    @FXML
    private ImageView jassem;

    @FXML
    private ImageView marwen;
    @FXML
    private ImageView logo;
    @FXML
    private ImageView khat;
    @FXML
    private ImageView sourour;

    @FXML
    private ImageView user;

    @FXML
    private JFXButton logout;

    @FXML
    void jassem(MouseEvent event) {

    }

    @FXML
    void maha(MouseEvent event) {

    }

    @FXML
    void marwen(MouseEvent event) {

    }

    @FXML
    void siwar(MouseEvent event) {

    }

    @FXML
    void sourour(MouseEvent event) {

    }

    @FXML
    void yassin(MouseEvent event) {

    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
         Image img1 =  new Image("images/maha.png");
        maha.setImage(img1);
        
        Image img2 =  new Image("images/siwar.png");
        siwar.setImage(img2);
        
        Image img3 = new Image("images/jassem.png");
        jassem.setImage(img3);
        
        Image img4 = new Image("images/marwen2.png");
        marwen.setImage(img4);
        
        Image img5= new Image("images/sourour.png");
        sourour.setImage(img5);
        
        Image img6 = new Image("images/yassin.png");
        yassin.setImage(img6);
        
        Image img = new Image("images/menulogo.png");
        logo.setImage(img);
        
        Image khat1 = new Image("images/khat.png");
        khat.setImage(khat1);
       // ooh so comment me now (yaani hatitni commsantaire) you butch i m watching youuuuuuu 
    }    
    
}
