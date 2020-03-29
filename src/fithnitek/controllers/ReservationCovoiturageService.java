/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;



import fithnitek.models.ReservationCovoiturage;
import fithnitek.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author lenovo
 */
public class ReservationCovoiturageService {
    Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouterrerservationocovoiturage(ReservationCovoiturage t) {
        try {
            String requete = "INSERT INTO reservation_covoiturage (idutilisateurr,idoffrer,nbrplacer,prixt) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getIdutilisateurr());
            pst.setInt(2, t.getIdoffrer());
            pst.setInt(3, t.getNumberplacer());
            pst.setFloat(4, t.getPrixt());
            
            pst.executeUpdate();
            System.out.println("Reservation ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public List<ReservationCovoiturage> afficherreservationcovoiturageutilisateur(int idu) {
        List<ReservationCovoiturage> list = new ArrayList<>();

        try {
            String requete = "SELECT * from reservation_covoiturage  Inner join offre_covoiturage on reservation_covoiturage.idoffrer = offre_covoiturage.idoffrecovoiturage Inner join fos_user on offre_covoiturage.idutilisateur = fos_user.id     and  idutilisateurr = ? ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,idu);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new ReservationCovoiturage(rs.getInt("idreservationcov"),rs.getInt("idutilisateurr"),rs.getInt("idoffrer"),rs.getInt("nbrplacer"),rs.getInt("prixt"),rs.getString("destination"),rs.getString("depart"),rs.getString("date"),rs.getString("username"),rs.getInt("tel"),rs.getInt("nbrplaceo")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    public void supprimerreservationcovoiturage(int id) {
        try {
            String requete = "DELETE FROM reservation_covoiturage WHERE idreservationcov=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("reservation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
