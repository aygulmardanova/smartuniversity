package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.AudEquipEntity;
import ru.kpfu.itis.entity.AuditoryEntity;
import ru.kpfu.itis.entity.EquipmentEntity;

import java.util.List;

@Repository
public interface AudEquipRepository extends JpaRepository<AudEquipEntity, Long> {

    List<AudEquipEntity> findAllByAuditory(AuditoryEntity auditory);

    List<AudEquipEntity> findAllByEquipment(EquipmentEntity equipment);

}
