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
public class Category {
    
    private int id_Category;
    private String title;
    private String description;
    private String type;
    private String nature;

    //For Retrieving a Category
    public Category(int id_Category, String title, String description, String type, String nature) {
        this.id_Category = id_Category;
        this.title = title;
        this.description = description;
        this.type = type;
        this.nature = nature;
    }

    //For New Category
    public Category(String title, String description, String type, String nature) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.nature = nature;
    }

       
    public int getId_Category() {
        return id_Category;
    }

    public void setId_Category(int id_Category) {
        this.id_Category = id_Category;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    @Override
    public String toString() {
        return "Category{" + "id_Category=" + id_Category + ", title=" + title + ", description=" + description + ", type=" + type + ", nature=" + nature + '}';
    }
    
    
}
