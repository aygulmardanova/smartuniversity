package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "aud_equip", schema = "uni", catalog = "smartuniversity")
public class AudEquipEntity {
    private Long id;
    private AuditoryEntity auditory;
    private EquipmentEntity equipment;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AudEquipEntity that = (AudEquipEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "aud_id", referencedColumnName = "id")
    public AuditoryEntity getAuditory() {
        return auditory;
    }

    public void setAuditory(AuditoryEntity auditory) {
        this.auditory = auditory;
    }

    @ManyToOne
    @JoinColumn(name = "equip_id", referencedColumnName = "id")
    public EquipmentEntity getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentEntity equipment) {
        this.equipment = equipment;
    }
}
