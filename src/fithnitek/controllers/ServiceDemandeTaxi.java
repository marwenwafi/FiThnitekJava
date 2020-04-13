/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import fithnitek.models.DemandeTaxi;
import fithnitek.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rawis
 */
public class ServiceDemandeTaxi  implements  IService <DemandeTaxi>{
  Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(DemandeTaxi d) {
        try {
            String requete = "INSERT INTO demande_taxi (`iduser`, `lieudedepart`, `lieudarrive`, `region`, `periode`, `date_d`, `etat`, `prix`) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
            PreparedStatement pst = cnx.prepareStatement(requete);
           pst.setInt(1, d.getIduser());
            pst.setString(2, d.getLieudepart());
               pst.setString(3, d.getLieuarrive());
             
                 pst.setString(4, d.getRegion());
                  pst.setString(5, d.getPeriode());
                  pst.setString(6, d.getDated());
                   pst.setInt(7, d.getEtat());
                    pst.setFloat(8, d.getPrix());
            pst.executeUpdate();
            System.out.println("Demande taxi  ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String requete = "DELETE FROM demande_taxi WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Demande taxi supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(DemandeTaxi d, int id) {
        try {
            String requete = "UPDATE demande_taxi SET iduser=?, lieudedepart=?, lieudarrive=?, region=?, periode=?, date_d=?, etat=?, prix=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(9, id);
              pst.setInt(1, d.getIduser());
            pst.setString(2, d.getLieudepart());
               pst.setString(3, d.getLieuarrive());
             
                 pst.setString(4, d.getRegion());
                  pst.setString(5, d.getPeriode());
                  pst.setString(6, d.getDated());
                   pst.setInt(7, d.getEtat());
                    pst.setFloat(8, d.getPrix());
            pst.executeUpdate();
            System.out.println("Demande taxi modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void modifieretat(int id,int etat) {
        try {
            
            String requete = "UPDATE demande_taxi SET etat=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, id);
              pst.setInt(1,etat );
           
            pst.executeUpdate();
            System.out.println("Etat Demande taxi modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<DemandeTaxi> afficher() {
              List<DemandeTaxi> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM demande_taxi";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    
     public List<DemandeTaxi> trieretat(int etat) {
              List<DemandeTaxi> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM demande_taxi where etat='"+etat+"' ORDER BY  date_d  ASC , periode ASC ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    public List<DemandeTaxi> afficheriddemande(int id ) {
              List<DemandeTaxi> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM demande_taxi when id=?";
            
            PreparedStatement pst = cnx.prepareStatement(requete);
              pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    
   
    
    /*
    public List<DemandeTaxi> afficheruser(int id) {
              List<DemandeTaxi> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM demande_taxi where iduser=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
    */
    
    
    
        public List<DemandeTaxi> afficheruser(int id) {
              List<DemandeTaxi> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM demande_taxi where iduser=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
        
        
     
     public List<DemandeTaxi> afficherclient(int idd) {
              List<DemandeTaxi> list = new ArrayList<>();
              Date actuelle = new Date();
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
           String dat = dateFormat.format(actuelle);
        try {
            String requete = "SELECT * FROM demande_taxi inner join fos_user  where demande_taxi.iduser = fos_user.id and date_d >= ? and   etat=0  AND  iduser!=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, dat);
            pst.setInt(2, idd);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
     
        public List<DemandeTaxi> afficheradmin() {
              List<DemandeTaxi> list = new ArrayList<>();
              Date actuelle = new Date();
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
           String dat = dateFormat.format(actuelle);
        try {
            String requete = "SELECT * FROM demande_taxi inner join fos_user  where demande_taxi.iduser = fos_user.id and date_d >= ?  and etat=0 ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, dat);
            //pst.setInt(2, idd);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
     
      
     public List<DemandeTaxi> affichertaxi(int idd) {
              List<DemandeTaxi> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM demande_taxi inner join fos_user  where demande_taxi.iduser = fos_user.id and    etat=0  AND  iduser=? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, idd);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
    }
     
   
     
     
     public List<DemandeTaxi> trieprix(int id) {
              List<DemandeTaxi> list = new ArrayList<>();
          Date actuelle = new Date();
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
           String dat = dateFormat.format(actuelle);
        try {
            String requete = "SELECT * FROM demande_taxi inner join fos_user  where demande_taxi.iduser = fos_user.id and date_d >= ?  and  etat=0  AND  iduser!=? ORDER BY prix  DESC , date_d  ASC , periode ASC";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, dat);
            pst.setInt(2, id);
          
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
     
    }
      public List<DemandeTaxi> triedate(int id) {
              List<DemandeTaxi> list = new ArrayList<>();
          Date actuelle = new Date();
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
           String dat = dateFormat.format(actuelle);
           System.out.println(dat);
        try {
            String requete = "SELECT * FROM demande_taxi inner join fos_user  where demande_taxi.iduser = fos_user.id  and date_d >= ? and etat=0  AND  iduser!=? ORDER BY  date_d  ASC , periode ASC";
            PreparedStatement pst = cnx.prepareStatement(requete);
           pst.setString(1, dat);
            pst.setInt(2, id);
            
          
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
     
    }
      
      
      
      
      public List<DemandeTaxi> triedateB() {
              List<DemandeTaxi> list = new ArrayList<>();
          Date actuelle = new Date();
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
           String dat = dateFormat.format(actuelle);
           System.out.println(dat);
        try {
            String requete = "SELECT * FROM demande_taxi inner join fos_user  where demande_taxi.iduser = fos_user.id  and date_d >= ? and etat=0   ORDER BY  date_d  ASC , periode ASC , prix DESC ";
            PreparedStatement pst = cnx.prepareStatement(requete);
           pst.setString(1, dat);
          //  pst.setInt(2, id);
            
          
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
     
    }
      
      
      
       public List<DemandeTaxi> trieheure(int id,String date) {
              List<DemandeTaxi> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM demande_taxi inner join fos_user  where demande_taxi.iduser = fos_user.id and    etat=0  AND  iduser!=?  ANS date_d=? ORDER BY  periode ASC";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.setString(2, date);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
     
    }
       
       
        public List<DemandeTaxi> recherchedateRegion(int id,String date,String region , String lieud) {
              List<DemandeTaxi> list = new ArrayList<>();
              
        try {
            String requete = "SELECT * FROM demande_taxi inner join fos_user  where demande_taxi.iduser = fos_user.id and    etat=0  AND  iduser!=?  AND date_d=?  AND region=?  AND lieudedepart=? ORDER BY  periode ASC";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.setString(2, date);
            pst.setString(3, region);
            pst.setString(4,lieud);
           // pst.setString(4, lieud);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
     
    }
        
        
        
        
         public List<DemandeTaxi> recherchedateRegionB(String date,String region , String lieud) {
              List<DemandeTaxi> list = new ArrayList<>();
              
        try {
            String requete = "SELECT * FROM demande_taxi inner join fos_user  where demande_taxi.iduser = fos_user.id and    etat=0   AND date_d=?  and region=?  AND lieudedepart=? ORDER BY  periode ASC";
            PreparedStatement pst = cnx.prepareStatement(requete);
         
            pst.setString(1, date);
            pst.setString(2, region);
            pst.setString(3, lieud);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new DemandeTaxi(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getFloat(9),rs.getString("username")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    
     
    }
    
}
