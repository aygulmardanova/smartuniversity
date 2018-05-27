package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_comp", schema = "uni", catalog = "smartuniversity")
public class UserCompEntity {
    private Integer id;
    private UserEntity userBy;
    private CompetenceEntity competence;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCompEntity that = (UserCompEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity getUserBy() {
        return userBy;
    }

    public void setUserBy(UserEntity userBy) {
        this.userBy = userBy;
    }

    @ManyToOne
    @JoinColumn(name = "comp_id", referencedColumnName = "id")
    public CompetenceEntity getCompetence() {
        return competence;
    }

    public void setCompetence(CompetenceEntity competence) {
        this.competence = competence;
    }
}
