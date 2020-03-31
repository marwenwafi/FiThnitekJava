/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fithnitek.models;

import java.io.Serializable;

/**
 *
 * @author sourour
 */
public class Questionnaire implements Serializable{
    private int id;
    private String question;
    private String reponse1;
    private String reponse2;
    private Event idevent ;

    public Questionnaire() {
    }

    public Questionnaire(String question, String reponse1, String reponse2, Event event) {
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.idevent = event;
    }

    public Questionnaire(int id, String question, String reponse1, String reponse2, Event idevent) {
        this.id = id;
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.idevent = idevent;
    }
    
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse1() {
        return reponse1;
    }

    public void setReponse1(String reponse1) {
        this.reponse1 = reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }

    public void setReponse2(String reponse2) {
        this.reponse2 = reponse2;
    }

    public Event getIdevent() {
        return idevent;
    }

    public void setIdevent(Event idevent) {
        this.idevent = idevent;
    }

   

    @Override
    public String toString() {
        return "Questuionnaire{"  + " question=" + question
                + ", reponse1=" + reponse1 + ", reponse2=" + reponse2 
                + ", event=" + idevent + '}';
    }
    
}
