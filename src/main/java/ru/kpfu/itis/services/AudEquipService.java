package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.AudEquipEntity;
import ru.kpfu.itis.entity.AuditoryEntity;
import ru.kpfu.itis.entity.EquipmentEntity;

import java.util.List;

public interface AudEquipService {

    List<AudEquipEntity> getAllByAuditory(AuditoryEntity auditory);

    List<AudEquipEntity> getAllByEquipment(EquipmentEntity equipment);

    List<AuditoryEntity> getAllAuditoriesByListOfEquipment(List<EquipmentEntity> requiredEquipments);
}
