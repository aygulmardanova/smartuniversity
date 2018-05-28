package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "aud_equip", schema = "uni", catalog = "smartuniversity")
public class AudEquipEntity extends IdObject<Long>  {

    @ManyToOne
    @JoinColumn(name = "aud_id", referencedColumnName = "id")
    private AuditoryEntity auditory;

    @ManyToOne
    @JoinColumn(name = "equip_id", referencedColumnName = "id")
    private EquipmentEntity equipment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AudEquipEntity that = (AudEquipEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public AuditoryEntity getAuditory() {
        return auditory;
    }

    public void setAuditory(AuditoryEntity auditory) {
        this.auditory = auditory;
    }

    public EquipmentEntity getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentEntity equipment) {
        this.equipment = equipment;
    }
}
