package com.tactfactory.mynotes.entities;

import android.os.Parcel;

import com.tactfactory.mynotes.entities.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tactfactory on 20/11/17.
 */

public class Note extends BaseEntity {
    private String name;
    private List<Enregistrement> enregistrementList;

    public Note(){
        this.enregistrementList = new ArrayList<Enregistrement>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Enregistrement> getEnregistrementList() {
        return enregistrementList;
    }

    public void setEnregistrementList(List<Enregistrement> enregistrementList) {
        this.enregistrementList = enregistrementList;
    }
}
