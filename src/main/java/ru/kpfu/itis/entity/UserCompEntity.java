package ru.kpfu.itis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "user_comp", schema = "uni", catalog = "smartuniversity")
public class UserCompEntity extends IdObject<Long> {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userBy;

    @ManyToOne
    @JoinColumn(name = "comp_id", referencedColumnName = "id")
    private @Getter @Setter CompetenceEntity competence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCompEntity that = (UserCompEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
