package ru.kpfu.itis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "equipment", schema = "uni", catalog = "smartuniversity")
public class EquipmentEntity extends IdObject<Long> {

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentEntity that = (EquipmentEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }
}
