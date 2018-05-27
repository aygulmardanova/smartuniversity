package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "auditory", schema = "uni", catalog = "smartuniversity")
public class AuditoryEntity {
    private Integer id;
    private String name;
    private Integer capacity;
    private Integer bulk;
    private Integer floor;
    private Collection<AudEquipEntity> audEquips;

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

    @Basic
    @Column(name = "capacity", nullable = true)
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "bulk", nullable = true)
    public Integer getBulk() {
        return bulk;
    }

    public void setBulk(Integer bulk) {
        this.bulk = bulk;
    }

    @Basic
    @Column(name = "floor", nullable = true)
    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditoryEntity that = (AuditoryEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(capacity, that.capacity) &&
                Objects.equals(bulk, that.bulk) &&
                Objects.equals(floor, that.floor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, capacity, bulk, floor);
    }

    @OneToMany(mappedBy = "auditory")
    public Collection<AudEquipEntity> getAudEquips() {
        return audEquips;
    }

    public void setAudEquips(Collection<AudEquipEntity> audEquips) {
        this.audEquips = audEquips;
    }
}
