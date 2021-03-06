package ru.kpfu.itis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "subj_comp", schema = "uni", catalog = "smartuniversity")
public class SubjCompEntity extends IdObject<Long> {

    @ManyToOne
    @JoinColumn(name = "subj_id", referencedColumnName = "id")
    private SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name = "comp_id", referencedColumnName = "id")
    private CompetenceEntity competence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjCompEntity that = (SubjCompEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
