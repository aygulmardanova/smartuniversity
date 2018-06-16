package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.EquipmentEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.entity.WishEntity;

import java.util.List;
import java.util.Map;

public interface WishService {

    void save(WishEntity wish);

    void save(List<WishEntity> wishes);

    List<EquipmentEntity> getTeacherRequiredEquipmentForSubject(UserEntity teacher, SubjectEntity subject);

    List<EquipmentEntity> getRequiredEquipmentForSubject(SubjectEntity subject);

    void saveTimeWish(Long fromUserId, Integer pairStNum, Integer pairEndNum, Integer weekDay, Long subjectId);

    List<WishEntity> getWishesForUser(UserEntity user);

    List<WishEntity> getWishesForUserOrderByWeekDay(UserEntity user);

    void generateWishes();

    void generateStudToStudWishes();

    int getPointForStudCompetences(UserEntity stud1, UserEntity stud2);

    Map<UserEntity, Integer> getSortedByValueSimilarityMap(Map<UserEntity, Integer> similarity);

    void generateStudToStudForSubjectWishes();

    void generateTeachToStudWishes();

    Integer getPointForTeachSubjCompetences(UserEntity teacher, SubjectEntity subject);

    Integer getPointForInterests(UserEntity user1, UserEntity user2);

    Integer getPointForTimeWishes(UserEntity user1, UserEntity user2, SubjectEntity subject);

    int getPointForTwoTimeValues(int user1Time, int user2Time);

    void generateTeachToStudForSubjectWishes();

    void generateTeachToSubjAudWishes();

    void generateSubjectToAudWishes();

    Boolean isWishExist(WishEntity wish);
}
