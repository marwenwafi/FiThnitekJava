/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek;

import fithnitek.controllers.ServiceEvent;
import fithnitek.controllers.ServiceQuestionnaire;
import fithnitek.models.Event;
import fithnitek.models.Questionnaire;
import java.sql.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sourour
 */
public class EventTest extends Application{
    
    
     
    
    @Override
    public void start(Stage stage) throws Exception {
        
        
       

                       /********************************/
                          /*********EVENT*********/
                           /*******************************/   
                            
     
         Date dated=new Date(System.currentTimeMillis()) ; 
         Date datef=new Date(System.currentTimeMillis()) ; 
        Event e = new Event("JAVA FX",dated,datef,"java test ",11,"www.java.com","image","Questionnaire");
       System.out.println(e);
        ServiceEvent sevent=new ServiceEvent();
       
       
       /**************** AJOUTER  *****************************/
       // sevent.ajouter(e);
       //System.out.println("Ajouté");
      
      
      /**************** SUPPRIMER  *****************************/
       // sevent.supprimer(86);
        //System.out.println("Supprimé");
        
        
      /**************** MODIFIER  *****************************/
      /*Event ev= sevent.getEventById(88);
       ev.setDescription("test update javaa FX");
       ev.setPromotion(33);
       sevent.modifier(ev);*/

      /**************** AFFICHER  *****************************/
        
     //sevent.afficher().forEach(System.out::println);
     
      /**************** Activer Event ************************/
         //sevent.activerEvent(88);
     /******************** Desactiver Event *****************/
     
         //sevent.desactiverEvent(88);
         
      /*************************** Participer *****************************/
      sevent.participer(71, 5);
         
         
                        /********************************/
                          /********* QUESTIONNAIRE*********/
                           /*******************************/   
                         
          
         Event event =sevent.getEventById(88); 
         Questionnaire quest = new Questionnaire("test java FX questionnaire FX ","ok","ko",event);
         //System.out.println(quest);
         ServiceQuestionnaire squest=new ServiceQuestionnaire();
         
       /**************** AJOUTER  *****************************/
          //squest.ajouter(quest);
         //System.out.println("Ajouté");
      
      /**************** SUPPRIMER  *****************************/
        //squest.supprimer(78);
       // System.out.println("Supprimé");
        
        
      /**************** MODIFIER  *****************************/
       /*Questionnaire q=squest.getQuestionnaireById(79);
       q.setReponse1("oui");
       squest.modifier(q);*/
       
       
       /*
       quest.setId(80);
       quest.setQuestion("test update java FX sur Questionnaire 1 ");
       squest.modifier(quest);
       System.out.println("aprés modification "+quest);*/
       
        

      /**************** AFFICHER  *****************************/
        
     // squest.afficher().forEach(System.out::println);
      
    

    

      
            
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       
    
       
        Parent root = FXMLLoader.load(getClass().getResource("mainLogin.fxml"));
        
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

    
