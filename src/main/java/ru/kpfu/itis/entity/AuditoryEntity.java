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
@Table(name = "auditory", schema = "uni", catalog = "smartuniversity")
public class AuditoryEntity extends IdObject<Long> {

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;

    @Basic
    @Column(name = "capacity", nullable = true)
    private Integer capacity;

    @Basic
    @Column(name = "bulk", nullable = true)
    private Integer bulk;

    @Basic
    @Column(name = "floor", nullable = true)
    private Integer floor;

    @OneToMany(mappedBy = "auditory", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<AudEquipEntity> audEquips;

    @OneToMany(mappedBy = "auditory", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<AudCompEntity> audComps;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditoryEntity that = (AuditoryEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(name, that.name) &&
                Objects.equals(capacity, that.capacity) &&
                Objects.equals(bulk, that.bulk) &&
                Objects.equals(floor, that.floor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name, capacity, bulk, floor);
    }

}
