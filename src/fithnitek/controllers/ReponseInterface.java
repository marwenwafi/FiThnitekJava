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
 * @param <Y>
 */
public interface ReponseInterface<T,Y> {
    public void ajouterReponse(T t);
    public void modifierReclamation(int id);
    public List<Y> afficherReclamation();
    
}
