package com.epam.koretskyi.commission.db.entity;

import java.io.Serializable;

/**
 * Basic common parent for all entities. Provides id field and get/set methods
 * for him.
 *
 * @author D.Koretskyi on 22.09.2020.
 */
public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 1594263175837543032L;

    private int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
