package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.EquipmentEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.entity.WishEntity;

import java.util.List;

public interface WishService {

    void save(WishEntity wish);

    List<EquipmentEntity> getRequiredEquipmentForSubject(SubjectEntity subject);

    List<WishEntity> getWishesForUser(UserEntity user);

    void generateWishes(String wishType);

    void generateStudToStudWishes();

    void generateStudToStudForSubjectWishes();

    void generateTeachToStudWishes();

    void generateTeachToStudForSubjectWishes();

    void generateTeachToAudWishes();

    void generateSubjectToAudWishes();

    Boolean isWishExist(WishEntity wish);
}
