package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "subject", schema = "uni", catalog = "smartuniversity")
public class SubjectEntity {
    private Integer id;
    private String name;
    private Collection<SubjCompEntity> subjComps;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectEntity that = (SubjectEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "subject")
    public Collection<SubjCompEntity> getSubjComps() {
        return subjComps;
    }

    public void setSubjComps(Collection<SubjCompEntity> subjComps) {
        this.subjComps = subjComps;
    }
}
