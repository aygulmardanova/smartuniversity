package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.*;
import ru.kpfu.itis.entity.enums.WishStatusEnum;
import ru.kpfu.itis.entity.enums.WishTypeEnum;
import ru.kpfu.itis.repositories.WishRepository;
import ru.kpfu.itis.services.*;

import java.util.*;

@Service
public class WishServiceImpl implements WishService {

    @Autowired
    UserService userService;

    @Autowired
    WishInfoService wishInfoService;

    @Autowired
    WishStatusService wishStatusService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    AudEquipService audEquipService;

    @Autowired
    AuditoryService auditoryService;

    @Autowired
    WishRepository wishRepository;

    @Override
    public void save(WishEntity wish) {
        wishRepository.save(wish);
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
        List<UserEntity> students = userService.getAllStudents();
        for (int i = 0; i < students.size() - 1; i++) {

            Map<UserEntity, Integer> countMap = new TreeMap<>();
            for (int j = i; j < students.size(); j++) {
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
    public List<EquipmentEntity> getRequiredEquipmentForSubject(SubjectEntity subject) {
        WishInfoEntity subjectToEquipmentWishInfo = wishInfoService.getWishInfoByType(WishTypeEnum.SUBJ_EQUIP);
        List<WishEntity> wishEntities = wishRepository.findWishEntitiesBySubjectAndWishInfo(subject, subjectToEquipmentWishInfo);
        List<EquipmentEntity> requiredEquipmentForSubject = new ArrayList<>();
        wishEntities.forEach(wishEntity -> requiredEquipmentForSubject.add(wishEntity.getEquipment()));
        return requiredEquipmentForSubject;
    }

    @Override
    public void generateSubjectToAudWishes() {
        List<WishEntity> wishEntities = new ArrayList<>();
        for (SubjectEntity subject : subjectService.getAllSubjects()) {
            List<EquipmentEntity> requiredEquipmentForSubject = getRequiredEquipmentForSubject(subject);
            List<AuditoryEntity> auditories = audEquipService.getAllAuditoriesByListOfEquipment(requiredEquipmentForSubject);
            auditories.forEach(auditory -> {
                WishEntity wish = new WishEntity();
                wish.setSubject(subject);
                wish.setAuditory(auditory);
                wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.AUTO));
                wish.setWishInfo(wishInfoService.getWishInfoByType(WishTypeEnum.SUBJ_AUD));
                wishEntities.add(wish);
            });
        }
        wishRepository.save(wishEntities);
    }

    @Override
    public Boolean isWishExist(WishEntity wish) {
        List<WishEntity> wishes = wishRepository.findWishEntitiesByWishInfo(wish.getWishInfo());
        for (WishEntity wishEntity : wishes) {
            if (wishEntity.equals(wish))
                return true;
        }
        return false;
    }
}
