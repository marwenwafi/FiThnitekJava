/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;

/**
 *
 * @author yassin
 */
public class Evaluation {
    private int id;
    private int idClient;
    private int idUser;
    private int note;
    private String comment;

    public Evaluation(int idClient, int idUser, int note, String comment) {
        this.idClient = idClient;
        this.idUser = idUser;
        this.note = note;
        this.comment = comment;
    }

    public Evaluation(int id, int idClient, int idUser, int note, String comment) {
        this.id = id;
        this.idClient = idClient;
        this.idUser = idUser;
        this.note = note;
        this.comment = comment;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Evaluation{" + "id=" + id + ", idClient=" + idClient + ", idUser=" + idUser + ", note=" + note + ", comment=" + comment + '}';
    }
    
    
    
    
}
