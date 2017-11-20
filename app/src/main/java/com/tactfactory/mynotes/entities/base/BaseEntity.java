package com.tactfactory.mynotes.entities.base;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by tactfactory on 20/11/17.
 */

public abstract class BaseEntity implements Serializable {
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
