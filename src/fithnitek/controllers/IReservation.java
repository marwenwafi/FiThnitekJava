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
import fithnitek.models.Reservation_Colis;
import java.util.List;

/**
 *
 * @author USER
 */
public interface IReservation <T>{
   
    public List<Reservation_Colis> afficherReservationColis(int id);
    public void  ajouterOffreReservation(Reservation_Colis O);
     public void modifierOffreReservation(Reservation_Colis O);
    public void supprimerOffreReservation(Reservation_Colis O) ;
       //  public List<Reservation_Colis> afficherReservationColisAll() ;
    public List<Reservation_Colis> afficherReservationColisAll() ;


}
