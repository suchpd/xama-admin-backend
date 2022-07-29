package com.xama.backend.infrastructure.common;

import javax.persistence.*;

@MappedSuperclass
public abstract class Entity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "Id",
            nullable = false
    )
    private int id;

    public Entity() {
    }

    public int getId() {
        return this.id;
    }

    private void setId(final int id) {
        this.id = id;
    }
}
