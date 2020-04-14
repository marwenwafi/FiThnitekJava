/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import java.util.List;

/**
 *
 * @author yassin
 * @param <T>
 */
public interface ReclamationInterface<T> {
    public void ajouterReclamation(T t);
    public void supprimerReclamation(T t);
    public void modifierReclamation(int id);
    public List<T> afficherReclamation();
    
}
