/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.Repositroies.IServiceEvent;
import fithnitek.models.Event;
import fithnitek.models.User;
import fithnitek.utils.DataSource;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author sourour
 */
public class ServiceEvent implements IServiceEvent<Event> {
    Connection cnx=DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Event e) {
        System.out.println("cc");
        String rqt="INSERT INTO event (titre,dateDebut,dateFin,description "
                + ",promotion,etat,image,operation,url) Values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst=cnx.prepareStatement(rqt);
            pst.setString(1, e.getTitre());
            pst.setDate(2, e.getDateDebut());
            pst.setDate(3, e.getDateFin());
            pst.setString(4, e.getDescription());
            pst.setInt(5, e.getPromotion());
            pst.setString(6, "En attente");
            pst.setString(7, e.getImage());
            pst.setString(8, e.getOperation());
            pst.setString(9, e.getUrl());
            pst.executeUpdate();
            System.out.println("Evenement ajouté");
            
            
            
        } catch (SQLException ex) {
            ex.getMessage();
        }
        
        
      }

    @Override
    public void supprimer(int id)  {
        String rqt="DELETE FROM event WHERE id=? ";
         
        try {
            
            PreparedStatement pr = cnx.prepareStatement(rqt);
            pr.setInt(1, id);
            pr.executeUpdate();
            System.out.println("Evenement supprimé ");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
    }

    @Override
    public void modifier(Event e) {
         try {
            
           String rqt="UPDATE event SET dateDebut=? ,dateFin=?"
                + ",description=?"
                + ",promotion=?"
                + ",etat=?"
                + ",image=?"
                + ",operation=?"
                + ",titre=?"
                +",url=? WHERE id=?";
            PreparedStatement prd=cnx.prepareStatement(rqt);
            prd.setDate(1, e.getDateDebut());
            prd.setDate(2, e.getDateFin());
            prd.setString(3, e.getDescription());
            prd.setInt(4, e.getPromotion());
            prd.setString(5 , getEventById(e.getId()).getEtat());
            prd.setString(6, e.getImage());
            prd.setString(7, e.getOperation());
            prd.setString(8, e.getTitre());
            prd.setString(9, e.getUrl());
            prd.setInt(10, e.getId());
            
            prd.executeUpdate();
            System.out.println("Evenement modifié");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
    
    }

    @Override
    public List<Event> afficher() {
        
        List<Event> events=new ArrayList<Event>();
        String rqt="SELECT * FROM event";
        try {
            PreparedStatement ps=cnx.prepareStatement(rqt);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               
               // System.out.println(rs.getString(9));
                
                
                 events.add(new Event(rs.getInt(1),rs.getString(9),rs.getDate(2),rs.getDate(3),
                         rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(10)
                         ,rs.getString(7),rs.getString(8)
                 ));
                
                
            }
            
            
           
                    
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
       return events;
    }
    /*************************************************/
    /*************** Calcul **************************/
    /************************************************/
    public Event getEventById(int id){
        
         String rqtimb="SELECT * FROM event WHERE id = ? ";
         Event x=new Event();
        PreparedStatement pss;
        try {
            pss = cnx.prepareStatement(rqtimb);
             pss.setInt(1,id);
               ResultSet rss=pss.executeQuery();
               
               
              
               if(rss.next())
               {
                    x = new Event(id,rss.getString(9),rss.getDate(2),rss.getDate(3),
                         rss.getString(4),rss.getInt(5),rss.getString(6),rss.getString(10)
                         ,rss.getString(7),rss.getString(8));
                   
                   
               }
               
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;      
    }
    
    
    
    public void activerEvent(int id) 
    {
        Event event=this.getEventById(id);
        //java.sql.Array x=null ;
        
        
        String rqt="UPDATE event SET etat='Visible' where id=?";
        //String rqtNotif="INERT INTO notification_event (idevent,title,description) VALUES (?,?,?)";
        String q="INSERT INTO notification_event( idevent, title, description ,"
                + " notification_date, seen ,route) VALUES (?,?,?,?,?,?)";
        
        
        
        try {
            
            PreparedStatement prd=cnx.prepareStatement(rqt);
            prd.setInt(1, id);
            PreparedStatement prdNotif=cnx.prepareStatement(q);
            prdNotif.setInt(1,id );
            prdNotif.setString(2, event.getTitre());
            prdNotif.setString(3, ""+event.getPromotion());
            prdNotif.setDate(4,new Date(System.currentTimeMillis()));
            prdNotif.setBoolean(5, false);
            prdNotif.setString(6, "fi_thnitek_readoneevent");
           // prdNotif.setString(4, "teeeessstttt");
           // prdNotif.setArray(5,  x);
            
            
            
            
            prd.executeUpdate();
            prdNotif.executeUpdate();
            
            System.out.println("Evénement activé !");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    public void desactiverEvent(int id)
    {
        String rqt="DELETE FROM notification_event WHERE idevent=? ";
        String rqtetat="UPDATE event SET etat='Invisible' where id=?"; 
        try {
            
            PreparedStatement pr = cnx.prepareStatement(rqt);
            pr.setInt(1, id);
            PreparedStatement pr2 = cnx.prepareStatement(rqtetat);
            pr2.setInt(1, id);
            
            pr.executeUpdate();
            pr2.executeUpdate();
            
            System.out.println("Evenement desactivé ! ");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void participer(int id,int iduser){
        //ici je vais récupéré l utilisateur courant et éliminer le parametre iduser
    /* User usr=new User(33,"", "username","prenom", 7777, new Date(2000,15,10)
             , new Date(2000,15,10), 1, 100000);*/
     
     
     Event event=this.getEventById(id);
       
        
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
    
    public void repondre(){
        
        
    }
 
 
    
}
