/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FiThnitek.models;

import java.util.Date;

/**
 *
 * @author yassin
 */
public class Reclamation {
    private int id;
    private String type;
    private String sujet;
    private String description;
    private int etat;
    private Date date;

    public Reclamation(int id, String type, String sujet, Date date) {
        this.id = id;
        this.type = type;
        this.sujet = sujet;
        this.date = date;
    }

   

    public Reclamation(int id, String type, String sujet ,String description) {
        this.id = id;
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

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type=" + type + ", sujet=" + sujet + ", description=" + description + ", etat=" + etat + ", date=" + date + '}';
    }
    
    
    
    
}
