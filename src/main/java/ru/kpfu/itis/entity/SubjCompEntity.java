package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subj_comp", schema = "uni", catalog = "smartuniversity")
public class SubjCompEntity {
    private Integer id;
    private SubjectEntity subject;
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
        SubjCompEntity that = (SubjCompEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "subj_id", referencedColumnName = "id")
    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
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
