/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;
import fithnitek.models.*;

import fithnitek.utils.DataSource;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author USER
 */
public class ServiceReservationColis implements IReservation<Reservation_Colis> {
            Connection cnx = DataSource.getInstance().getCnx();
            
  @Override
    public List<Reservation_Colis> afficherReservationColis(int id) {
        List<Reservation_Colis> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reservation_colis where idUR="+id+"";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

            //    System.out.print(rs.getInt(1));
             //   System.out.println("/n");
                    //public Reservation_Colis( int id, int id_Offre, double HauteurResv, double LargeurResv, double LongueurResv, int PrixResv) {
               list.add(new Reservation_Colis(rs.getInt(1),rs.getDouble(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5),rs.getInt(6),rs.getDouble(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
     @Override
    public List<Reservation_Colis> afficherReservationColisAll() {
        List<Reservation_Colis> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reservation_colis ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {

            //    System.out.print(rs.getInt(1));
             //   System.out.println("/n");
                    //public Reservation_Colis( int id, int id_Offre, double HauteurResv, double LargeurResv, double LongueurResv, int PrixResv) {
               list.add(new Reservation_Colis(rs.getInt(1),rs.getDouble(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5),rs.getInt(6),rs.getDouble(7)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
   
    @Override
    public void ajouterOffreReservation(Reservation_Colis O) {
        try {
            //if(GetLongueur(id_Offre)>0)
        if((O.getLongueurResv()<=GetLongueur(O.getId_Offre()))&&(O.getLargeurResv()<=GetLargeur(O.getId_Offre()))&&(GetLargeur(O.getId_Offre())>0)&&(GetLongueur(O.getId_Offre())>0)&&(GetHauteur(O.getId_Offre())>0)&&(O.getHauteurResv()<=GetHauteur(O.getId_Offre())))
          {     
              double PrixR=O.getLargeurResv()*O.getHauteurResv()*O.getLongueurResv()*GetPrix(O.getId_Offre());
       //  String requete1 = "UPDATE  offre_colis SET Hauteur="+(GetHauteur(id_Offre)-GetHauteurResv(id_Offre))+" WHERE id_Offre_Col="+id_Offre+"";
           String requete = "INSERT INTO Reservation_Colis (hauteur_resv,largeur_resv,prix,idUR,idOffre,longueur_resv) VALUES (?,?,'"+O.getLargeurResv()*O.getHauteurResv()*O.getLongueurResv()*GetPrix(O.getId_Offre())+"',?,?,?) ";
           PreparedStatement pst = cnx.prepareStatement(requete);

           pst.setDouble(1,  O.getHauteurResv()) ; 
           pst.setDouble(2,  O.getLargeurResv());
           // pst.setInt(3,O.getPrixResv());
            pst.setInt(3,O.getId());
            pst.setInt(4,O.getId_Offre());
            pst.setDouble(5, O.getLongueurResv());
            
            pst.executeUpdate();
          String requete1 = "UPDATE  offre_colis SET Largeur=Round("+(GetLargeur(O.getId_Offre())-O.getLargeurResv())+",2),Longueur=Round("+(GetLongueur(O.getId_Offre())-O.getLongueurResv())+",2) WHERE id_Offre_Col="+O.getId_Offre()+"";
          PreparedStatement pst1 = cnx.prepareStatement(requete1);

                     pst1.executeUpdate();

            System.out.println("Reservation ajoutée !");
            
            }
            else
            {
                System.out.println("leeee");
            }
//////////////
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } /*     catch (WriterException ex) {   
                    Logger.getLogger(ServiceReservationColis.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ServiceReservationColis.class.getName()).log(Level.SEVERE, null, ex);
                }  */ 
        ////////////////
    }

       public Double GetPrix (int id_offre)  throws SQLException {
            String prix = "SELECT Prix FROM offre_colis  where id_Offre_Col = "+id_offre+"";
                        PreparedStatement pst = cnx.prepareStatement(prix);
            ResultSet rs = pst.executeQuery();

               rs.next();    
               Double Prix=rs.getDouble(1);
               return Prix;
     
    }
            @Override
          public void modifierOffreReservation(Reservation_Colis O) {
         try {
            String requete = "UPDATE  Reservation_Colis SET hauteur_resv=?,largeur_resv=?,longueur_resv=? WHERE id_Reservation_colis=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
          
            pst.setDouble(1,  O.getHauteurResv()) ; 
            pst.setDouble(2, O.getLargeurResv());
           // pst.setInt(8,O.getId());
            pst.setDouble(3,  O.getLongueurResv());
            pst.setInt(4,O.getId_ReservationCol());

            pst.executeUpdate();
            System.out.println("Reservation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
        @Override
    public void supprimerOffreReservation(int id) {
          try {
            String requete = "DELETE FROM Reservation_Colis  WHERE  id_Reservation_colis="+id+" ";
            PreparedStatement pst = cnx.prepareStatement(requete);
      //      pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Reservation  supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
          
    }
 public Double GetHauteur (int id)  throws SQLException {
            String Hauteur = "SELECT Hauteur FROM offre_colis  where id_Offre_Col = "+id+"";
                        PreparedStatement pst = cnx.prepareStatement(Hauteur);
            ResultSet rs = pst.executeQuery();

               rs.next();    
               Double HauteurOffre=rs.getDouble(1);
               return HauteurOffre;
     
    }
  public Double GetLargeur (int id)  throws SQLException {
            String largeur = "SELECT Largeur FROM offre_colis  where id_Offre_Col = "+id+"";
                        PreparedStatement pst = cnx.prepareStatement(largeur);
            ResultSet rs = pst.executeQuery();

               rs.next();    
               Double largeurOffre=rs.getDouble(1);
               return largeurOffre;
     
    }
   public Double GetLongueur (int id)  throws SQLException {
            String Longueur = "SELECT Longueur FROM offre_colis  where id_Offre_Col = "+id+"";
                        PreparedStatement pst = cnx.prepareStatement(Longueur);
            ResultSet rs = pst.executeQuery();

               rs.next();    
               Double LongueurOffre=rs.getDouble(1);
               return LongueurOffre;
     
    }
      
 
    
}
