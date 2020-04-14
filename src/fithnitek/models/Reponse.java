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
public class Reponse {
    private int id;
    private int idRec;
    private String reply;

    public Reponse(int idRec, String reply) {
        this.idRec = idRec;
        this.reply = reply;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", idRec=" + idRec + ", reply=" + reply + '}';
    }
    
    
    
}
