package ru.openvoleyballclub.model;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    protected Integer id;
    protected String name;
    protected LocalDateTime creationTime;

    public boolean isNew() {
        return id == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }
}
