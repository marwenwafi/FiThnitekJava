/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;
import fithnitek.models.*;


import java.util.List;

/**
 *
 * @author USER
 */
public interface IReservation <T>{
        //public List<T> afficherOffreColis();
   // public void ajouterOffreColis(T o);
     //  public void supprimerOffreColis(T o);
     // public void modifierOffreColis(Offre_Colis t);
   //          public List<T> RechercheOffreColis(int Prix);
    public List<Reservation_Colis> afficherReservationColis(int id);
    public void  ajouterOffreReservation(Reservation_Colis O);
     public void modifierOffreReservation(Reservation_Colis O);
     public void supprimerOffreReservation(int id);
       //  public List<Reservation_Colis> afficherReservationColisAll() ;
    public List<Reservation_Colis> afficherReservationColisAll() ;


}
