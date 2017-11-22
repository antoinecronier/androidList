package com.tactfactory.mynotes.entities;

import com.tactfactory.mynotes.entities.base.BaseEntity;

/**
 * Created by tactfactory on 20/11/17.
 */

public class Enregistrement extends BaseEntity {
    private String contenu;
    private double note_id;

    public Enregistrement(){

    }

    public Enregistrement(String contenu, double note_id){
        this.contenu = contenu;
        this.note_id = note_id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public double getNote_id() {
        return note_id;
    }

    public void setNote_id(double note_id) {
        this.note_id = note_id;
    }
}
