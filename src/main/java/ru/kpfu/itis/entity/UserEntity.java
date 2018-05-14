package ru.kpfu.itis.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "uni", name = "user")
public class UserEntity extends IdObject<Long> {

    private String surname;

    private String name;

    private String patronym;



}