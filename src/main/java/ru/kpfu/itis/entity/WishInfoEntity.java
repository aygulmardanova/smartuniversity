package ru.kpfu.itis.entity;

import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.entity.enums.WishTypeEnum;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "wish_info", schema = "uni", catalog = "smartuniversity")
public class WishInfoEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "type", nullable = true, length = -1)
    private String type;

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishInfoEntity that = (WishInfoEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name);
    }
}
