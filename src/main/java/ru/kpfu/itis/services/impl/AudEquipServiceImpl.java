package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.AudEquipEntity;
import ru.kpfu.itis.entity.AuditoryEntity;
import ru.kpfu.itis.entity.EquipmentEntity;
import ru.kpfu.itis.repositories.AudEquipRepository;
import ru.kpfu.itis.services.AudEquipService;
import ru.kpfu.itis.services.AuditoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AudEquipServiceImpl implements AudEquipService {

    @Autowired
    AudEquipRepository audEquipRepository;

    @Autowired
    AuditoryService auditoryService;

    @Override
    public List<AudEquipEntity> getAllByAuditory(AuditoryEntity auditory) {
        return audEquipRepository.findAllByAuditory(auditory);
    }

    @Override
    public List<AudEquipEntity> getAllByEquipment(EquipmentEntity equipment) {
        return audEquipRepository.findAllByEquipment(equipment);
    }

    @Override
    public List<AuditoryEntity> getAllAuditoriesByListOfEquipment(List<EquipmentEntity> requiredEquipments) {
        List<AuditoryEntity> auditories = new ArrayList<>();
        for (EquipmentEntity equipment : requiredEquipments) {
            List<AuditoryEntity> auditoryForEquipment =
                    getAllByEquipment(equipment).stream().map(audEquipEntity -> audEquipEntity.getAuditory()).collect(Collectors.toList());
            auditories.retainAll(auditoryForEquipment);
        }
        return auditories;
    }

}
