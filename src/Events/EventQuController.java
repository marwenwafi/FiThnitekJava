/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import fithnitek.controllers.ServiceEvent;
import fithnitek.models.Event;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import jdk.internal.dynalink.support.NameCodec;


/**
 * FXML Controller class
 *
 * @author sourour
 */
public class EventQuController implements Initializable {
    

    @FXML
    private TextField titre;
    @FXML
    private DatePicker dateFin;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private TextField url;
    @FXML
    private Button Ajouter;
    @FXML
    private TextField promotion;
    @FXML
    private TextField description;
    @FXML
    private ChoiceBox<String> option;
    @FXML
    private Button image;
    @FXML
    Pagination pagination;
    
    
    
    @FXML
    private TableColumn<Event, String> titreCo;
    @FXML
    private TableColumn<Event, DatePicker> dd;
    @FXML
    private TableColumn<Event, DatePicker> df;
    @FXML
    private TableColumn<Event, String> erco;
    @FXML
    private TableColumn<Event, String> descco;
    @FXML
    private TableColumn<Event, Integer> promoco;
    @FXML
    private TableColumn<Event, String> imgco;
    @FXML
    private TableColumn<Event, String> opeco;
    @FXML
    private TableColumn<Event, String> urlco;
    @FXML
    private TableView<Event> tab;
    String statusClick ,statusCode;
     ServiceEvent sv= new ServiceEvent();
     String extension="";
     
    @FXML
    private TableColumn<Event, Integer> idevent;
    
    private final static int rowsPerPage = 11;
    public static String rq;
    public static String eventpart;
    public static String Verifimage ;
    FileChooser file=new FileChooser();
    @FXML
    private VBox vbMenu;
    @FXML
    private TextField fileLocation;
    Image image2 ;
    @FXML
    private ImageView imgSelected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            //imgco.setCellValueFactory(new StringP);
            titreCo.setCellValueFactory(new PropertyValueFactory<>("titre"));
            dd.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            df.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            erco.setCellValueFactory(new PropertyValueFactory<>("etat"));
            descco.setCellValueFactory(new PropertyValueFactory<>("description"));
            promoco.setCellValueFactory(new PropertyValueFactory<>("promotion"));
            imgco.setCellValueFactory(new PropertyValueFactory<>("image"));
            opeco.setCellValueFactory(new PropertyValueFactory<>("operation"));
            urlco.setCellValueFactory(new PropertyValueFactory<>("url"));
            option.getItems().add("Questionnaire");
            option.getItems().add("Publicité");
            idevent.setCellValueFactory(new PropertyValueFactory<>("id"));
            afficherEvent();
            tab.getSelectionModel().clearSelection();
            
      

    }    

    @FXML
    private void ajouterEvent(ActionEvent event)  {
        try{
            
        
        QuestionnaireQuController c =new QuestionnaireQuController(); 
        Event e=new Event();
        e.setTitre(titre.getText());
        e.setOperation(option.getValue());
       e.setPromotion(Integer.parseInt(promotion.getText()));
        e.setImage(fileLocation.getText()+"."+extension); //generateUniqueFileName()+fileLocation.getText()
        
        e.setDescription(description.getText());
        e.setUrl("http://"+url.getText());
        e.setDateDebut(Date.valueOf(dateDebut.getValue()));
       e.setDateFin(Date.valueOf(dateFin.getValue()));
       if(verifier("")==true)
       {
           sv.ajouter(e);
           rq=e.getTitre();
           afficherEvent();
       }
       
       
       
       if(e.getOperation().equals("Questionnaire"))
       {
            try {
                gestquest(event);
                //c.getIdevent().getItems().add(1);
                
                //System.out.println(sv.getidBytitre(e.getTitre()));
            } catch (Exception ex) {
                Logger.getLogger(EventQuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        }catch(NullPointerException e)
        {
            verifier("Vous devez remplir tous les champs !");
        }catch(NumberFormatException e){
            verifier("La promotion doit etre un chiffre !");
            
        }
        
        
    }

    @FXML
    private void modifierEvent(ActionEvent event) {
        try{
         Event click = tab.getSelectionModel().getSelectedItems().get(0);
         System.out.println(click.getId());
        
        Event e=click;
        e.setId(click.getId());
        e.setTitre(titre.getText());
        e.setOperation(option.getValue());
        e.setPromotion(Integer.parseInt(promotion.getText()));
        if( !((fileLocation.getText()).equals(click.getImage()))){
                e.setImage(fileLocation.getText()+"."+extension);
        } 
       e.setDescription(description.getText());
       if(!((url.getText()).equals(click.getUrl()))){
           e.setUrl("http://"+url.getText());
       }
        
        e.setDateDebut(Date.valueOf(dateDebut.getValue()));
        e.setDateFin(Date.valueOf(dateFin.getValue()));
         System.out.println("update"+e );
         if(verifier("")==true)
         {
             sv.modifier(e);
             afficherEvent();
         }
             
      
        }catch(NumberFormatException e){
            verifier("La promotion doit etre un chiffre !");
            
        }catch(NullPointerException e)
        {
            verifier("Vous devez remplir tous les champs !");
        }
         
         
       
      
    }
     

    @FXML
    private void deleteEvent(ActionEvent event) {
        Event click = tab.getSelectionModel().getSelectedItems().get(0);
        System.out.println("suppression)" +click.getId());
        
        sv.supprimer(click.getId());
        afficherEvent();
        
    }

    

    private void afficherEvent() {
        int x= (sv.afficher().size() / rowsPerPage + 1);
        pagination.setPageCount(x);
        pagination.setPageFactory((Integer param) ->createPage(param));
        
      
        
    }
    private Node createPage(int pageIndex) {
        System.out.println("je suis ici");
        ObservableList<Event> eventData = FXCollections.observableArrayList();
        List  <Event> l=new ArrayList<Event>();
                l=sv.afficher();
        for(Event e :l){
            eventData.add(e);
        } 
        int fromIndex = pageIndex * rowsPerPage;
        int toIndex = Math.min(fromIndex + rowsPerPage, eventData.size());
        tab.setItems(FXCollections.observableArrayList(eventData.subList
        (fromIndex, toIndex)
        ));
        System.out.println(tab);

      return  tab;
    }


    @FXML
    private void geteventt(MouseEvent event) {
        
        Event click = tab.getSelectionModel().getSelectedItems().get(0);
        
        System.out.println(click.getTitre());
        titre.setText(click.getTitre());
        dateDebut.setValue(LocalDate.parse(click.getDateDebut().toString()));
        dateFin.setValue(LocalDate.parse(click.getDateFin().toString()));
        description.setText(click.getDescription());
        promotion.setText(""+click.getPromotion());
        url.setText(click.getUrl());
        option.setValue(click.getOperation());
        fileLocation.setText(click.getImage());
       
       System.out.println(click.getImage());
       
        
       
        
    }
    String generateUniqueFileName() {
    String filename = "fe";
    //long millis = System.currentTimeMillis();
    String datetime = new Date(System.currentTimeMillis()).toGMTString();
    datetime = datetime.replace(" ", "");
    datetime = datetime.replace(":", "");
    String rndchars =""+Math.random();
    rndchars=rndchars.replace(".", "fe");
    filename = rndchars + "_" + datetime ;
    return filename;
}

   

    @FXML
    private void desactiverEvent(ActionEvent event) {
        Event click = tab.getSelectionModel().getSelectedItems().get(0);
        int id= click.getId() ;
        sv.desactiverEvent(id);
        afficherEvent();
        
    }

    @FXML
    private void activerEvent(ActionEvent event) {
        Event click = tab.getSelectionModel().getSelectedItems().get(0);
        int id= click.getId() ;
        sv.activerEvent(id);
        eventpart=click.getTitre();
        afficherEvent();
        
        try {
            notificationEtFidelite(event);
        } catch (Exception ex) {
            Logger.getLogger(EventQuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /***************************************************/
    /*************    CHOOSE IMAGE   *****************/
    /***************************************************/
    
    @FXML
    private void ChoisirImage(ActionEvent event) {
        
        List<String> extentions=new ArrayList();
        extentions.add("*.png");
        extentions.add("*.jpeg");
        extentions.add("*.jpg");
        Window stage = vbMenu.getScene().getWindow();
        file.setTitle("");
        file.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier Image",extentions));
        
       File file2=file.showOpenDialog(stage);
       
        
    if(file2 != null)
    {
        fileLocation.setText(generateUniqueFileName());
         extension =file2.getName().substring(file2.getName().lastIndexOf(".")+1);
         System.out.println("exten"+extension);
        fileLocation.setText(generateUniqueFileName());
        System.out.println("file2.getPath()=====" +file2.getPath());
        
        System.out.println(file2.getName());
        BufferedImage bImage;
        Verifimage=file2.getAbsolutePath();
        Image im=new Image(file2.getAbsoluteFile().toURI().toString());
        imgSelected.setImage(im);
        
            
        
        
            
        
        
        
        //fileLocation.setText(generateUniqueFileName()+);
            try {
                bImage = ImageIO.read(file2);
                ImageIO.write(bImage, extension, new File("C://wamp64/www/PiDev/web/uploads/eventsImages/"+fileLocation.getText()+"."+extension));
            } catch (IOException ex) {
                Logger.getLogger(EventQuController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            


    }
    
       
    }
    
   /*public static void saveToFile(Image image) {
    File outputFile = new File("C:\\wamp64\\www\\PiDev\\web\\uploads\\eventsImages\\");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    try {
        ImageIO.write(bImage, "png", outputFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }*/
    public boolean verifier(String s){
        
        if(titre.getText().equals("") || description.getText().equals("")||
           promotion.getText().equals("") || fileLocation.getText().equals("") ||
            option.getValue()== null ||
                dateDebut.getValue() == null || dateFin.getValue()==null
                
                )
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"You must fill all the fields ");
        alert.initStyle(StageStyle.DECORATED.UTILITY);
        alert.setTitle("Warning");
        alert.showAndWait();
           return false;  
        }
        if (description.getText().length()< 6){
         Alert alert = new Alert(Alert.AlertType.ERROR,"Description size must be greater than 6 characters!");
        alert.initStyle(StageStyle.DECORATED.UTILITY);
        alert.setTitle("Warning");
        alert.showAndWait();
           return false;  
        }
         int resu=dateDebut.getValue().compareTo(dateFin.getValue());
        if(resu>0)
        {
            new Alert(Alert.AlertType.ERROR, "End Date must be equal to or greater than start date !", ButtonType.OK).show();
            return false;  
        }
        if(dateDebut.getValue().compareTo(LocalDate.now())<0){
            new Alert(Alert.AlertType.ERROR, "Start Date must be equal to or greater than current date !", ButtonType.OK).show();
            return false;  
        }
        if(dateFin.getValue().compareTo(LocalDate.now())<0){
            new Alert(Alert.AlertType.ERROR, "End Date must be equal to or greater than current date !", ButtonType.OK).show();
            return false;  
        }
        
       return true;      
    
        
    }

   
    
    
    /********************MENU**************************/
     @FXML
    private void notificationEtFidelite(ActionEvent event) throws Exception {               
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Notificationetfid.fxml"));
        //
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        closeButtonAction(event);
    } catch(Exception e) {
        e.printStackTrace();
    }
        
    }
    
    
    
    
    
     @FXML
    private void gestquest(ActionEvent event) 
        throws Exception {               
    try {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuestionnaireQu.fxml"));
        
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        closeButtonAction(event);
    } catch(Exception e) {
        e.printStackTrace();
    }
}

    @FXML
    private void toEvents(ActionEvent event) throws Exception {               
    try {
        
        
        closeButtonAction(event);
        toFront(event);
    } catch(Exception e) {
        e.printStackTrace();
    }
    }
    
     /******************* REDIRECT **************************/
    private void closeButtonAction(ActionEvent e){
  
       // Platform.exit();
       final Node source = (Node) e.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
 
}
    private void toFront(ActionEvent event) throws Exception {               
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EventQu.fxml"));
        
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
       
    } catch(Exception e) {
        e.printStackTrace();
    }
    }
    
    
    
   }
        
       

    

