/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.Repositroies.IServiceQuestionnaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import fithnitek.models.Event;
import fithnitek.models.Questionnaire;
import fithnitek.utils.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author sourour
 */
public class ServiceQuestionnaire implements IServiceQuestionnaire<Questionnaire>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Questionnaire t) {
        String rqt="INSERT INTO questionnaire(question,reponse1,reponse2,idevent) VALUES (?,?,?,?)";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(rqt);
            pst.setString(1, t.getQuestion());
            pst.setString(2, t.getReponse1());
            pst.setString(3, t.getReponse2());
            pst.setObject (4,t.getIdevent().getId());
            pst.executeUpdate();
            System.out.println("Questionnaire ajouté"); 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestionnaire.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }

    @Override
    public void supprimer(int id) {
       String req="DELETE FROM questionnaire WHERE id=?  ";
        try {
            PreparedStatement pst=cnx.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Questionnaire supprimé");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestionnaire.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public void modifier(Questionnaire t) {
        
        String rqt="UPDATE questionnaire SET question=?,reponse1=?,reponse2=?,idevent=? WHERE id=?";
        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(rqt);
            pst.setString(1, t.getQuestion());
            pst.setString(2, t.getReponse1());
            pst.setString(3, t.getReponse2());
            pst.setObject (4,t.getIdevent().getId());
            pst.setInt(5, t.getId());
            pst.executeUpdate();
            System.out.println("Questionnaire modifié"); 
        } catch (SQLException ex) {
            Logger.getLogger(ServiceQuestionnaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        }

    @Override
    public List<Questionnaire> afficher() {
        List<Questionnaire> quest=new ArrayList<Questionnaire>();
        String rqt="SELECT * FROM questionnaire ORDER BY id DESC";
        String rqtimb;
         
        try {
            PreparedStatement ps=cnx.prepareStatement(rqt);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               rqtimb="SELECT * FROM event WHERE id = ? ";
               PreparedStatement pss=cnx.prepareStatement(rqtimb);
               pss.setInt(1,rs.getInt(2));
               ResultSet rss=pss.executeQuery();
               Event x=new Event();
               
              
               if(rss.next())
               {
                    x = new Event(rss.getInt(1),rss.getString(9),rss.getDate(2),rss.getDate(3),
                         rss.getString(4),rss.getInt(5),rss.getString(6),rss.getString(10)
                         ,rss.getString(7),rss.getString(8));
                   
                   
               }
               
               
                
               quest.add(new Questionnaire(
                       rs.getInt(1),
                       rs.getString(3),
                       rs.getString(4),
                       rs.getString(5),x
               ));
                
                
            }
            
            
           
                    
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
       return quest;
    }
        /***************************************/
    
    
    public Questionnaire getQuestionnaireById(int id){
        
       Questionnaire ques=new Questionnaire();
        try {
            String rqt="SELECT * FROM questionnaire where id=?";
        String rqtimb;
         
        
            PreparedStatement ps=cnx.prepareStatement(rqt);
            ps.setInt(1, id);
            
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
               rqtimb="SELECT * FROM event WHERE id = ? ";
               PreparedStatement pss=cnx.prepareStatement(rqtimb);
               pss.setInt(1,rs.getInt(2));
               ResultSet rss=pss.executeQuery();
               Event x=new Event();
               
              
               if(rss.next())
               {
                    x = new Event(rss.getString(9),rss.getDate(2),rss.getDate(3),
                         rss.getString(4),rss.getInt(5),rss.getString(6),rss.getString(10)
                         ,rss.getString(7),rss.getString(8));
                   
                   
               }
               
               
                
               ques= new Questionnaire(id,
                       rs.getString(3),
                       rs.getString(4),
                       rs.getString(5),x
               );
                
                
            }
            
            
           
                    
               
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ques;      
    }
    
    
    
    
    public Event getEventBytitre(String titre){
        
         String rqtimb="SELECT * FROM event WHERE titre = ? ";
         Event x=new Event();
        PreparedStatement pss;
        try {
            pss = cnx.prepareStatement(rqtimb);
             pss.setString(1,titre);
               ResultSet rss=pss.executeQuery();
               
               
              
               if(rss.next())
               {
                    x = new Event(rss.getInt(1),rss.getString(9),rss.getDate(2),rss.getDate(3),
                         rss.getString(4),rss.getInt(5),rss.getString(6),rss.getString(10)
                         ,rss.getString(7),rss.getString(8));
                   
                   
               }
               
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;      
    }
    }
    
    
    
    

