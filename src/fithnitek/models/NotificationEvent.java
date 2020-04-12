/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;


import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author sourour
 */
public class NotificationEvent {
    private int id;
    private int idevent;
    private String title;
    private String description;
    private String icon;
    private String route;
    private String route_parameters;
    private Date notification_date ;
    private boolean seen;

    public NotificationEvent() {
        this.notification_date = new Date(11);
        this.seen=false;
       // route_parameters=new ArrayList();
        
    }

    public NotificationEvent(int id, String title, String description, Date notification_date) {
        this.id = id;
        this.notification_date = notification_date;
        this.title = title;
        this.description = description;
        this.route = route;
       
    }

    
    
    
    

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRoute_parameters() {
        return route_parameters;
    }

    public void setRoute_parameters(String route_parameters) {
        this.route_parameters = route_parameters;
    }

    

    public Date getNotification_date() {
        return notification_date;
    }

    public void setNotification_date(Date notification_date) {
        this.notification_date = notification_date;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    @Override
    public String toString() {
        return "NotificationEvent{" + "id=" + id + ", idevent=" + idevent + ", title=" + title 
                + ", description=" + description + ", icon=" + icon + ", route_parameters=" 
                + route_parameters + ", notification_date=" + notification_date + ", seen=" 
                + seen + '}';
    }
    
    
    
}
