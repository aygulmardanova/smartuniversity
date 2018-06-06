package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.*;
import ru.kpfu.itis.entity.enums.WishTypeEnum;
import ru.kpfu.itis.repositories.WishRepository;
import ru.kpfu.itis.services.SubjectService;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.services.WishService;
import ru.kpfu.itis.services.WishInfoService;

import java.util.*;

@Service
public class WishServiceImpl implements WishService {

    @Autowired
    UserService userService;

    @Autowired
    WishInfoService wishInfoService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    WishRepository wishRepository;

    @Override
    public void save(WishEntity wish) {
        wishRepository.save(wish);
    }

    @Override
    public List<EquipmentEntity> getRequiredEquipmentForSubject(SubjectEntity subject) {
        WishInfoEntity subjectToEquipmentWishInfo = wishInfoService.getWishInfoByType(WishTypeEnum.SUBJ_EQUIP);
        List<WishEntity> wishEntities = wishRepository.findWishEntitiesBySubjectAndWishInfo(subject, subjectToEquipmentWishInfo);
        List<EquipmentEntity> equipments = new ArrayList<>();
        wishEntities.forEach(wishEntity -> equipments.add(wishEntity.getEquipment()));
        return equipments;
    }

    @Override
    public List<WishEntity> getWishesForUser(UserEntity user) {
        return wishRepository.findWishEntitiesByFromUser(user);
    }

    @Override
    public void generateWishes(String wishType) {

    }

    @Override
    public void generateStudToStudWishes() {
        List<UserEntity> users = userService.getAllUsers();
        for (int i = 0; i < users.size() - 1; i++) {
            Map<UserEntity, Integer> countMap = new TreeMap<>();
            for (int j = i; j < users.size(); j++) {
                int count = 0;

            }

        }
    }

    @Override
    public void generateStudToStudForSubjectWishes() {

    }

    @Override
    public void generateTeachToStudWishes() {

    }

    @Override
    public void generateTeachToStudForSubjectWishes() {

    }

    @Override
    public void generateTeachToAudWishes() {

    }

    @Override
    public void generateSubjectToAudWishes() {

        for (SubjectEntity subject: subjectService.getAllSubjects()) {
            List<EquipmentEntity> requiredEquipmentForSubject = getRequiredEquipmentForSubject(subject);

            List<SubjCompEntity> subjComps = subject.getSubjComps();
//            List<>
        }
    }
}
