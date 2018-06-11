package ru.kpfu.itis.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "subject", schema = "uni", catalog = "smartuniversity")
public class SubjectEntity extends IdObject<Long> {

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private @Getter @Setter String name;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private @Getter @Setter List<SubjCompEntity> subjComps;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectEntity that = (SubjectEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name);
    }
}