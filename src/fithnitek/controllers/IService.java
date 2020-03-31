/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FiThnitek.services;

import java.util.List;

/**
 *
 * @author yassin
 * @param <T>
 */
public interface IService<T> {
    public void ajouterReclamation(T t);
    public void supprimerReclamation(T t);
    public void modifierReclamation(T t);
    public List<T> afficherReclamation();
}
