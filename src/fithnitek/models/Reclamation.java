/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;

import java.util.Date;

/**
 *
 * @author yassin
 */
public class Reclamation {
    private int id;
    private int idUtilisateur;
    private String type;
    private String sujet;
    private String description;
    private int etat;
    private String date;

    public Reclamation(int id, int idUtilisateur, String type, String sujet, String description, int etat, String date) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.type = type;
        this.sujet = sujet;
        this.description = description;
        this.etat = etat;
        this.date = date;
    }

    public Reclamation(String type, String sujet, String description) {
        this.type = type;
        this.sujet = sujet;
        this.description = description;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

  

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", idUtilisateur=" + idUtilisateur + ", type=" + type + ", sujet=" + sujet + ", description=" + description + ", etat=" + etat + ", date=" + date + '}';
    }
    
    
    
}
