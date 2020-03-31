/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;

import java.sql.Date;

/**
 *
 * @author marwe
 */
public class LeaderBoard {
    
    private int idleaderboard;
    private String title;
    private String description;
    private int size;
    private Date start_date;
    private Date end_date;
    private int category;
    private String color;
    private String image;
    private Date updatedAt;

    //For Retrieving a LeaderBoard
    public LeaderBoard(int idleaderboard, String title, String description, int size, Date start_date, Date end_date, int category, String color, String image, Date updatedAt) {
        this.idleaderboard = idleaderboard;
        this.title = title;
        this.description = description;
        this.size = size;
        this.start_date = start_date;
        this.end_date = end_date;
        this.category = category;
        this.color = color;
        this.image = image;
        this.updatedAt = updatedAt;
    }
    
    //For New LeaderBoard
    public LeaderBoard(String title, String description, int size, Date start_date, Date end_date, int category, String color, String image, Date updatedAt) {
        this.title = title;
        this.description = description;
        this.size = size;
        this.start_date = start_date;
        this.end_date = end_date;
        this.category = category;
        this.color = color;
        this.image = image;
        this.updatedAt = updatedAt;
    }
    
    
    public int getIdleaderboard() {
        return idleaderboard;
    }

    public void setIdleaderboard(int idleaderboard) {
        this.idleaderboard = idleaderboard;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "LeaderBoard{" + "idleaderboard=" + idleaderboard + ", title=" + title + ", description=" + description + ", size=" + size + ", start_date=" + start_date + ", end_date=" + end_date + ", category=" + category + ", color=" + color + ", image=" + image + ", updatedAt=" + updatedAt + '}';
    }
    
}
