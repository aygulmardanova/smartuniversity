package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.AuditoryEntity;
import ru.kpfu.itis.entity.CompetenceEntity;
import ru.kpfu.itis.entity.EquipmentEntity;

import java.util.List;

public interface AuditoryService {

    List<AuditoryEntity> getAllAuditories();

    AuditoryEntity getByName(String name);

    Boolean isAuditoryHaveEquipment(AuditoryEntity auditory, List<EquipmentEntity> requiredEquipment);

    Double isAuditoryDevelopCompetences(AuditoryEntity auditory, List<CompetenceEntity> developedCompetence);
}
