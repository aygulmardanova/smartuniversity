package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(schema = "uni", name = "user_role")
public class UserRoleEntity extends IdObject<Long> implements Serializable {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}