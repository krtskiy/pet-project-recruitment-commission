package com.epam.koretskyi.commission.db.entity;

import java.io.Serializable;

/**
 * @author D.Koretskyi on 22.09.2020.
 */
public class Entity implements Serializable {
    private static final long serialVersionUID = 1594263175837543032L;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
