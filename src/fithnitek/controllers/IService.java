/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.controllers;

import java.util.List;

/**
 *
 * @author Rawis
 */
public interface IService <T> {
      public void ajouter(T t);
    public void supprimer( int id);
    public void modifier(T t, int id);
    public List<T> afficher();
    
    
}
