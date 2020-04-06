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
public interface IColis <T>{
        public List<Offre_Colis> RechercheOffreColisAdmin(Double Hauteur,Double Largeur,Double Longueur,String car,String Date);
 public List<Offre_Colis> afficherOffremshColis(int id);
        public List<T> afficherOffreColis(int id);
    public void ajouterOffreColis(T o);
       public void supprimerOffreColis(T o);
      public void modifierOffreColis(Offre_Colis t);
  //  public List<Offre_Colis> RechercheOffreColis(String Date) ;
              public List<Offre_Colis> RechercheOffreColisDepartArrive(String Depart,String Arrive,int id,String Date) ;
public List<Offre_Colis> afficherAllColis();
     public List<Offre_Colis> TrierOffreColis() ;
          public List<Offre_Colis> TrierOffreColisPrix() ;
  

}
