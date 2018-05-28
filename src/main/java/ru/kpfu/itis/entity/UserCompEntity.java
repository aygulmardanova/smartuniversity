package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_comp", schema = "uni", catalog = "smartuniversity")
public class UserCompEntity extends IdObject<Long> {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userBy;

    @ManyToOne
    @JoinColumn(name = "comp_id", referencedColumnName = "id")
    private CompetenceEntity competence;

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

    public UserEntity getUserBy() {
        return userBy;
    }

    public void setUserBy(UserEntity userBy) {
        this.userBy = userBy;
    }

    public CompetenceEntity getCompetence() {
        return competence;
    }

    public void setCompetence(CompetenceEntity competence) {
        this.competence = competence;
    }
}
