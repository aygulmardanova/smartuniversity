package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.EquipmentEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.WishEntity;

import java.util.List;

public interface WishService {

    List<EquipmentEntity> getRequiredEquipmentForSubject(SubjectEntity subject);

    void generateWishes(String wishType);

    void generateStudToStudWishes();

    void generateStudToStudForSubjectWishes();

    void generateTeachToStudWishes();

    void generateTeachToStudForSubjectWishes();

    void generateTeachToAudWishes();

    void generateSubjectToAudWishes();
}
