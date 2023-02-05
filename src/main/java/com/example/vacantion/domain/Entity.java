package com.example.vacantion.domain;

import java.io.Serial;
import java.io.Serializable;

/**
 * A general Entity class
 * @param <ID> - the type of the ID
 */
public class Entity<ID> implements Serializable {

    @Serial
    private static final long serialVersionUID = 7331115341259248461L;

    protected ID id;
    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }
}