/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.Repositroies;

import java.util.List;

/**
 *
 * @author sourour
 */
public interface IServiceEvent <Event>{
   
    public void ajouter(Event e);
    
    public void supprimer(int id);
    public void modifier(Event e);
    public List<Event> afficher();
    
    
}
    

