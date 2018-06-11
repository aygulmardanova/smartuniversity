package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.AuditoryEntity;
import ru.kpfu.itis.entity.CompetenceEntity;
import ru.kpfu.itis.entity.EquipmentEntity;
import ru.kpfu.itis.repositories.AuditoryRepository;
import ru.kpfu.itis.services.AuditoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditoryServiceImpl implements AuditoryService {

    @Autowired
    AuditoryRepository auditoryRepository;

    @Override
    public List<AuditoryEntity> getAllAuditories() {
        return auditoryRepository.findAll();
    }

    @Override
    public AuditoryEntity getByName(String name) {
        return auditoryRepository.findFirstByName(name);
    }

    @Override
    public Double isAuditoryDevelopCompetences(AuditoryEntity auditory, List<CompetenceEntity> developedCompetences) {
        List<CompetenceEntity> auditoryCompetences = new ArrayList<>();
        auditory.getAudComps().forEach(audCompEntity -> auditoryCompetences.add(audCompEntity.getCompetence()));

        List<CompetenceEntity> intersection =
                developedCompetences.stream().filter(auditoryCompetences::contains).collect(Collectors.toList());
        if (intersection == null || intersection.size() == 0)
            return 0.0;
        return (double) (intersection.size() / developedCompetences.size());
    }

    @Override
    public Boolean isAuditoryHaveEquipment(AuditoryEntity auditory, List<EquipmentEntity> requiredEquipment) {
        List<EquipmentEntity> auditoryEquipment = new ArrayList<>();
        auditory.getAudEquips().forEach(audEquipEntity -> auditoryEquipment.add(audEquipEntity.getEquipment()));

        final Boolean[] haveRequiredEquipment = {true};
        requiredEquipment.forEach(requiredEquipmentElement -> {
            if (!auditoryEquipment.contains(requiredEquipmentElement))
                haveRequiredEquipment[0] = false;
        });
        return haveRequiredEquipment[0];
    }
}
