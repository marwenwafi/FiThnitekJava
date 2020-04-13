/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;

/**
 *
 * @author marwe
 */
public class LeaderBoardValue {
    
    private int rank;
    private String username;
    private int value;

    public LeaderBoardValue(int rank, String username, int value) {
        this.rank = rank;
        this.username = username;
        this.value = value;
    }
    

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
    
}
