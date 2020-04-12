/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;

/**
 *
 * @author sourour
 */
public class Cadeaux {
    private int id;
    private String username;
    private String cadeaux;

    public Cadeaux(int id, String username, String cadeaux) {
        this.id = id;
        this.username = username;
        this.cadeaux = cadeaux;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCadeaux() {
        return cadeaux;
    }

    public void setCadeaux(String cadeaux) {
        this.cadeaux = cadeaux;
    }
    
    
    
}
