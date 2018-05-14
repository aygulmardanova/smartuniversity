package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
public class IdObject<T> implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    public IdObject() {
    }

    public IdObject(T id) {
        this.id = id;
    }
    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}