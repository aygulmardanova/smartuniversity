package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.EquipmentEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.entity.WishEntity;

import java.util.List;

public interface WishService {

    void save(WishEntity wish);

    void save(List<WishEntity> wishes);

    List<EquipmentEntity> getTeacherRequiredEquipmentForSubject(UserEntity teacher, SubjectEntity subject);

    List<EquipmentEntity> getRequiredEquipmentForSubject(SubjectEntity subject);

    void saveTimeWish(Long fromUserId, Integer pairStNum, Integer pairEndNum, Integer weekDay, Long subjectId);

    List<WishEntity> getWishesForUser(UserEntity user);

    void generateWishes();

    void generateStudToStudWishes();

    void generateStudToStudForSubjectWishes();

    void generateTeachToStudWishes();

    void generateTeachToStudForSubjectWishes();

    void generateTeachToSubjAudWishes();

    void generateSubjectToAudWishes();

    Boolean isWishExist(WishEntity wish);
}
