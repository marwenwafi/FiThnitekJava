/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;
import fithnitek.Repositroies.IServiceNotificationEvent;
import fithnitek.models.Cadeaux;
import fithnitek.models.Event;
import fithnitek.models.NotificationEvent;
import fithnitek.models.ParticipeEvent;
import fithnitek.models.Questionnaire;
import fithnitek.models.User;
import fithnitek.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/**
 *
 * @author sourour
 */
public class ServiceNotificationEvent implements IServiceNotificationEvent<NotificationEvent>{
     private static Connection cnx= DataSource.getInstance().getCnx();
    @Override
    public List<NotificationEvent> afficher() {
    List<NotificationEvent> events=new ArrayList<NotificationEvent>();
        String rqt="SELECT * FROM notification_event";
        
        try {
            PreparedStatement ps=cnx.prepareStatement(rqt);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               
                
                
                
                events.add(
                        new NotificationEvent(rs.getInt(1), rs.getString(3)
                                , rs.getString(4),
                                rs.getDate(8))
                );
                
                //tem.out.println("dans cette methode"+rs.getString(3));
            }
         } catch (SQLException ex) {
            ex.getMessage();
        }
       return events; 
   }
    /****************Partie FRONT***********************/
    public List<NotificationEvent> afficherFront() {
    List<NotificationEvent> events=new ArrayList<NotificationEvent>();
    List<Integer> allevents=new ArrayList<Integer>();
    List<Integer> allparticipants=new ArrayList<Integer>();
    List<ParticipeEvent> participants=Participants();
    List<NotificationEvent> events2=afficher();
    
    for(ParticipeEvent p :participants){
        String titre=p.getTitle();
        int id=getidBytitre(titre);
        allparticipants.add(id);
    }
    for(NotificationEvent e :events2){
        allevents.add(getideventFromNotif(e.getId()));
        
       
    }
   
    
     allevents.removeAll(allparticipants);
    for(Integer i:allevents)
    {
        events.add(getNotificationParidevent(i));
        
    }
    
        
    
       return events; 
   }
    
    
    
    /**************** Integrationnnn ************/
    public int getPointsById(int id)
    { 
        int points = 0;
        String rqt="SELECT points FROM fos_user WHERE id="+id;
        try {
           PreparedStatement pss = cnx.prepareStatement(rqt);
           ResultSet rss=pss.executeQuery();
               
               
              
               if(rss.next())
               {
                   points =rss.getInt(1); 
                   
                   
               }
               
        
        } catch (SQLException ex) {
            ex.getMessage();
        }
        
    
    
    
    
    return points;
        
    }
    
    
    /*************************Converssion ***************************/
    
    public void ConvertirPoints (String carnet ,int choix , int iduser)
    {
       int mespoints= getPointsById(iduser);
       if (mespoints >= choix ){
           
        String rqt="UPDATE fos_user SET points=? WHERE id=?";
        String cadeau="INSERT INTO convertirpoints (iduser,cadeau) Values (?,?) ";
        
         PreparedStatement ps,ps1;
           try {
                ps = cnx.prepareStatement(rqt);
                ps.setInt(2, iduser);
                ps.setInt(1, mespoints-choix);
                ps.executeUpdate();
                //tem.out.println("poitnsssssssss");
                ps1= cnx.prepareStatement(cadeau);
                ps1.setInt(1, iduser);
                ps1.setString(2, carnet);
                //tem.out.println("cadeauuuuuu");
                ps1.executeUpdate();
                
                
                
                
           } catch (SQLException ex) {
               Logger.getLogger(ServiceNotificationEvent.class.getName()).log(Level.SEVERE, null, ex);
           }
            
           
        
    }
       else{
                Alert e=new Alert(Alert.AlertType.ERROR, "Pas suffaisant !",ButtonType.OK);
               }
    }
    
    /**************************Participants************************************/
    public List<ParticipeEvent> Participants() {
    List<ParticipeEvent> pats=new ArrayList<ParticipeEvent>();
        String rqt="SELECT * FROM participe_event";
        
        try {
            PreparedStatement ps=cnx.prepareStatement(rqt);
            ResultSet rs=ps.executeQuery();
            
            while(rs.next()){
               
                
                pats.add(new ParticipeEvent(rs.getInt(1),  getUsernameById(rs.getInt(3))
                        ,getTitreById(rs.getInt(4)) )
                ) ;              
                
                
                //tem.out.println("dans Participants  " +getTitreById(rs.getInt(4)));
            }
         } catch (SQLException ex) {
            ex.getMessage();
        }
       return pats; 
   }
    
    public String getUsernameById(int id)
    { 
        String username = "";
        String rqt="SELECT username FROM fos_user WHERE id="+id;
        try {
           PreparedStatement pss = cnx.prepareStatement(rqt);
           ResultSet rss=pss.executeQuery();
               
               
              
               if(rss.next())
               {
                   username =rss.getString(1); 
                   
                   
               }
               
        
        } catch (SQLException ex) {
            ex.getMessage();
        }
        
    
    
    
    
    return username;
        
    }
    public String getTitreById(int id)
    { 
        String titre = "";
        String rqt="SELECT titre FROM event WHERE id="+id;
        try {
           PreparedStatement pss = cnx.prepareStatement(rqt);
           ResultSet rss=pss.executeQuery();
               
               
              
               if(rss.next())
               {
                   titre =rss.getString(1); 
                   
                   
               }
               
        
        } catch (SQLException ex) {
            ex.getMessage();
        }
        
    
    
    
    
    return titre;
        
    }
    
    
    
    /***********************Cadeaux****************************/
    
    public List<Cadeaux> Cadeaux() {
    List<Cadeaux> cadx=new ArrayList<Cadeaux>();
        String rqt="SELECT * FROM convertirpoints ";
        
        try {
            PreparedStatement ps=cnx.prepareStatement(rqt);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               
                
                cadx.add(new Cadeaux(rs.getInt(1),  getUsernameById(rs.getInt(2))
                        ,rs.getString(3))
                ) ;              
                
                
                //tem.out.println("dans Cadeaux  " +rs.getString(3));
            }
         } catch (SQLException ex) {
            ex.getMessage();
        }
       return cadx; 
   }
    /****************************  Participer  ************************/
    public void participer(int id,int iduser){
        //ici je vais récupéré l utilisateur courant et éliminer le parametre iduser
        String operation=getStringColonneByid(id);
        System.out.println("c'est leperation : " +operation);
        
        if(operation.equals("Publicité"))
        { Event event=this.getEventPart(id);
        String rqt="UPDATE fos_user SET points=? WHERE id=?";
        String points="SELECT points FROM fos_user WHERE id=? ";
        PreparedStatement ps,ps2,ps3;
        try {
            ps2=cnx.prepareStatement(points);
            ps2.setInt(1, iduser);
            ResultSet rs=ps2.executeQuery();
            int pt=0;
            if(rs.next())
            {
                 pt= rs.getInt(1);
            }
            
            
            
            ps=cnx.prepareStatement(rqt);
            
            ps.setInt(2, iduser);
            ps.setInt(1, pt+event.getPromotion());
            ps.executeUpdate();
            
            
            String participation="INSERT INTO participe_event (participe,userId,eventId)"
                    + " VALUES (?,?,?)";
            ps3=cnx.prepareStatement(participation);
            
            ps3.setBoolean(1, true);
            ps3.setInt(2, iduser);
            ps3.setInt(3, id);
            ps3.executeUpdate();
            //done
            
            System.out.println("vous avez participé à l'évènement "+event.getTitre());
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        
        
        
    }
    /*******************Reponder *****************************/
    public void repondre(int id ,int iduser){
        String operation=getStringColonneByid(id);
        System.out.println("c'est l'operation : " +operation);
        
        Event event=this.getEventPart(id);
        
        if(operation.equals("Questionnaire"))
        {
            
        String rqt="UPDATE fos_user SET points=? WHERE id=?";
        String points="SELECT points FROM fos_user WHERE id=? ";
        PreparedStatement ps,ps2,ps3;
        try {
            ps2=cnx.prepareStatement(points);
            ps2.setInt(1, iduser);
            ResultSet rs=ps2.executeQuery();
            int pt=0;
            if(rs.next())
            {
                 pt= rs.getInt(1);
            }
            
            
            
            ps=cnx.prepareStatement(rqt);
            
            ps.setInt(2, iduser);
            ps.setInt(1, pt+event.getPromotion());
            ps.executeUpdate();
            
            
            String participation="INSERT INTO participe_event (participe,userId,eventId)"
                    + " VALUES (?,?,?)";
            ps3=cnx.prepareStatement(participation);
            
            ps3.setBoolean(1, true);
            ps3.setInt(2, iduser);
            ps3.setInt(3, id);
            ps3.executeUpdate();
            //done
            
            System.out.println("vous avez participé à l'évènement "+event.getTitre());
            
         } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        }
        
    }
    
    /*********************Operations Get******************************/
    public int getidBytitre(String titre){
        
         String rqtimb="SELECT id FROM event WHERE titre =? ";
         int x=0;
        PreparedStatement pss;
        try {
            pss = cnx.prepareStatement(rqtimb);
             pss.setString(1,titre);
             ResultSet rss=pss.executeQuery();
               
               
              
               if(rss.next())
               {
                    x = rss.getInt(1);
                    
                   
                   
               }
               
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;      
    }
    public Event getEventPart(int id){
        
         Event cadx=new Event();
        String rqt="SELECT `id`, `dateDebut`, `dateFin`, `description`, `promotion`, `etat`, `image`,"
                + " `operation`, `titre`, `url`, `iduser` FROM `event` WHERE `id`="+id;
        
        try {
            PreparedStatement ps=cnx.prepareStatement(rqt);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               
                
                cadx=new Event(id, rs.getString(9), rs.getDate(3),
                        rs.getString(4), rs.getInt(5), rs.getString(6)
                        , rs.getString(10),rs.getString(7), rs.getString(8));
                              
                
                
                
            }
         } catch (SQLException ex) {
            ex.getMessage();
        }
       return cadx; 
   }
    
    public String getStringColonneByid(int id){
        
         String rqtimb="SELECT operation FROM event WHERE id = ? ";
         
         String x="";
        PreparedStatement pss;
        try {
            pss = cnx.prepareStatement(rqtimb);
            pss.setInt(1,id);
            ResultSet rss=pss.executeQuery();
              if(rss.next())
               {
                   System.out.println("fithnitek.controllers"
                           + ".ServiceNotificationEvent.getStringColonneByid()"
                           +rss.getString(1));
                    x = rss.getString(1);
              }
       } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;      
    }
    /*****************************************/
    public List<Questionnaire> afficherQuestList(int id) {
        List<Questionnaire> quest=new ArrayList<Questionnaire>();
        String rqt="SELECT * FROM questionnaire WHERE idevent=?";
        String rqtimb;
         
        try {
            PreparedStatement ps=cnx.prepareStatement(rqt);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               rqtimb="SELECT * FROM event WHERE id = ? ";
                System.out.println("afficherQuestList()"+rs.getString(3));
               quest.add(new Questionnaire(
                       rs.getString(3),
                       rs.getString(4),
                       rs.getString(5)
               ));  
            }           
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
       return quest;
    }
    /****************Get notifications******************/
    public NotificationEvent getNotificationParidevent(int id){
        
         NotificationEvent cadx=new NotificationEvent();
        String rqt="SELECT `id`, `idevent`, `title`, `description`, `icon`, `route`, `route_parameters`, `notification_date`, `seen` FROM `notification_event` WHERE `idevent`="+id;
        
        try {
            PreparedStatement ps=cnx.prepareStatement(rqt);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               
                
                cadx=new NotificationEvent(rs.getInt(1), rs.getString(3)
                                , rs.getString(4),
                                rs.getDate(8));
                              
                
                
                
            }
         } catch (SQLException ex) {
            ex.getMessage();
        }
       return cadx; 
   }
    
    public int getideventFromNotif(int id){
        int x=0;
        String rqt="SELECT `idevent` FROM `notification_event` WHERE id ="+id;
       try {
            PreparedStatement ps=cnx.prepareStatement(rqt);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                x=rs.getInt(1);
            }
         } catch (SQLException ex) {
            ex.getMessage();
        }
       return x; 
   }
   /* public int getideventFrompart(int id){
        int x=0;
        String rqt="SELECT `eventId` FROM `participe_event` WHERE `id`="+id;
       try {
            PreparedStatement ps=cnx.prepareStatement(rqt);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){}
         } catch (SQLException ex) {
            ex.getMessage();
        }
       return x; 
   */
    
    
   
}
