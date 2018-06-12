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
    UserSubjService userSubjService;

    @Autowired
    AudEquipService audEquipService;

    @Autowired
    AuditoryService auditoryService;

    @Autowired
    WishRepository wishRepository;

    @Autowired
    InterestService interestService;

    @Autowired
    AlliedInterestService alliedInterestService;

    @Autowired
    CompetenceService competenceService;

    @Override
    public void save(WishEntity wish) {
        wishRepository.save(wish);
    }

    @Override
    public void save(List<WishEntity> wishes) {
        wishRepository.save(wishes);
    }

    @Override
    public List<WishEntity> getWishesForUser(UserEntity user) {
        return wishRepository.findWishEntitiesByFromUser(user);
    }

    @Override
    public void generateWishes() {
    }

    @Override
    public void generateStudToStudWishes() {
        List<UserEntity> students = userService.getAllStudents();
        List<WishEntity> wishes = new ArrayList<>();
        for (int i = 0; i < students.size() - 1; i++) {
            Map<UserEntity, Integer> similarityWithStud = new HashMap<>();
            for (int j = i + 1; j < students.size(); j++) {
                int similarityCount = 0;
                similarityCount += getPointForTimeWishes(students.get(i), students.get(j), null);
                similarityCount += getPointForInterests(students.get(i), students.get(j));
                similarityCount += getPointForStudCompetences(students.get(i), students.get(j));

                similarityWithStud.put(students.get(j), similarityCount);
            }
            Map<UserEntity, Integer> similarityWithStudSorted = getSortedByValueSimilarityMap(similarityWithStud);
            int maxSimilarity = similarityWithStudSorted.values().stream().findFirst().orElse(0);
            int studFrom = i;
            similarityWithStudSorted.keySet().forEach(stud -> {
                if (maxSimilarity != 0
                        && similarityWithStudSorted.get(stud) != 0
                        && similarityWithStudSorted.get(stud) >= maxSimilarity / 2) {
                    WishEntity wish = new WishEntity();
                    wish.setFromUser(students.get(studFrom));
                    wish.setStudUser(stud);
                    wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.AUTO));
                    wish.setWishInfo(wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_STUD));
                    wishes.add(wish);
                }
            });
        }
        wishRepository.save(wishes);
    }

    private int getPointForStudCompetences(UserEntity stud1, UserEntity stud2) {
        int point = 0;
        List<CompetenceEntity> stud1Competences = competenceService.getCompetencesByUser(stud1);
        List<CompetenceEntity> stud2Competences = competenceService.getCompetencesByUser(stud2);
        List<CompetenceEntity> stud1IupCompetences = competenceService.getStudentIupCompetencesByUser(stud1);
        List<CompetenceEntity> stud2IupCompetences = competenceService.getStudentIupCompetencesByUser(stud2);

        for (CompetenceEntity stud1Competence : stud1Competences) {
            if (stud2IupCompetences.contains(stud1Competence))
                point++;
        }
        for (CompetenceEntity stud2Competence : stud2Competences) {
            if (stud1IupCompetences.contains(stud2Competence))
                point++;
        }
        for (CompetenceEntity stud1IupCompetence : stud1IupCompetences) {
            if (stud2IupCompetences.contains(stud1IupCompetence))
                point++;
        }
        return point;
    }

    private Map<UserEntity, Integer> getSortedByValueSimilarityMap(Map<UserEntity, Integer> similarity) {
        List<Map.Entry<UserEntity, Integer>> list = new LinkedList<>(similarity.entrySet());
        list.sort(Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(list);

        Map<UserEntity, Integer> similaritySorted = new LinkedHashMap<>();
        for (Map.Entry<UserEntity, Integer> entry : list) {
            similaritySorted.put(entry.getKey(), entry.getValue());
        }
        return similaritySorted;
    }


    @Override
    public void generateStudToStudForSubjectWishes() {
    }

    @Override
    public void generateTeachToStudWishes() {

        List<UserEntity> teachers = userService.getAllTeachers();
        List<UserEntity> students = userService.getAllStudents();
        Map<AbstractMap.SimpleEntry<UserEntity, UserEntity>, Integer> similarity = new HashMap<>();
        for (UserEntity teacher : teachers) {
            for (UserEntity student : students) {
                int similarityCount = 0;
                similarityCount += getPointForTimeWishes(teacher, student, null);
                similarityCount += getPointForInterests(teacher, student);
                similarityCount += getPointForTeachStudCompetences(teacher, student);

                similarity.put(new AbstractMap.SimpleEntry<>(teacher, student), similarityCount);
            }
        }
        Map<AbstractMap.SimpleEntry<UserEntity, UserEntity>, Integer> similaritySorted = getSortedBySimilarityMap(similarity);
        int maxSimilarity = similaritySorted.values().stream().findFirst().orElse(0);
        List<WishEntity> wishes = new ArrayList<>();
        similaritySorted.keySet().forEach(keyPair -> {
            if (similaritySorted.get(keyPair) >= maxSimilarity / 2) {
                System.out.println(similaritySorted.get(keyPair) + " teacher:" + keyPair.getKey().getSurname() + ". student: " + keyPair.getValue().getSurname());
                WishEntity wish = new WishEntity();
                wish.setFromUser(keyPair.getKey());
                wish.setStudUser(keyPair.getValue());
                wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.AUTO));
                wish.setWishInfo(wishInfoService.getWishInfoByType(WishTypeEnum.TEACH_TO_STUD));
                wishes.add(wish);
            }
        });
        wishRepository.save(wishes);
        System.out.println(maxSimilarity);
    }

    private Map<AbstractMap.SimpleEntry<UserEntity, UserEntity>, Integer> getSortedBySimilarityMap(Map<AbstractMap.SimpleEntry<UserEntity, UserEntity>, Integer> similarity) {
        List<Map.Entry<AbstractMap.SimpleEntry<UserEntity, UserEntity>, Integer>> list = new LinkedList<>(similarity.entrySet());
        list.sort(Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(list);

        Map<AbstractMap.SimpleEntry<UserEntity, UserEntity>, Integer> similaritySorted = new LinkedHashMap<>();
        for (Map.Entry<AbstractMap.SimpleEntry<UserEntity, UserEntity>, Integer> entry : list) {
            similaritySorted.put(entry.getKey(), entry.getValue());
        }
        return similaritySorted;
    }

    private Integer getPointForTeachStudCompetences(UserEntity teacher, UserEntity student) {
        int point = 0;
        List<CompetenceEntity> teacherCompetences = competenceService.getCompetencesByUser(teacher);
        List<CompetenceEntity> studentIupCompetences = competenceService.getStudentIupCompetencesByUser(student);
        for (CompetenceEntity studentCompetence : studentIupCompetences) {
            if (teacherCompetences.contains(studentCompetence))
                point++;
        }
        return point;
    }

    private Integer getPointForInterests(UserEntity user1, UserEntity user2) {
        int point = 0;
        if (user1.getUserInterests() == null || user1.getUserInterests().size() == 0
                || user2.getUserInterests() == null || user2.getUserInterests().size() == 0)
            return point;
        for (InterestEntity user1Interest : interestService.getInterestsByUser(user1)) {
            for (InterestEntity user2Interest : interestService.getInterestsByUser(user2)) {
                if (user1Interest.equals(user2Interest) || alliedInterestService.isAlliedInterests(user1Interest, user2Interest))
                    point += 1;
            }
        }
        return point;
    }

    private Integer getPointForTimeWishes(UserEntity user1, UserEntity user2, SubjectEntity subject) {
        WishInfoEntity userStartTimeWishInfo = wishInfoService.getWishInfoByType(WishTypeEnum.USER_START_TIME);
        WishInfoEntity userEndTimeWishInfo = wishInfoService.getWishInfoByType(WishTypeEnum.USER_END_TIME);
        WishInfoEntity userStartTimeOnSubjWishInfo;
        WishInfoEntity userEndTimeOnSubjWishInfo;
        List<WishEntity> user1Wishes;
        List<WishEntity> user2Wishes;
        user1Wishes = wishRepository.findWishEntitiesByFromUserAndWishInfo(user1, userStartTimeWishInfo);
        user1Wishes.addAll(wishRepository.findWishEntitiesByFromUserAndWishInfo(user1, userEndTimeWishInfo));
        user2Wishes = wishRepository.findWishEntitiesByFromUserAndWishInfo(user2, userStartTimeWishInfo);
        user2Wishes.addAll(wishRepository.findWishEntitiesByFromUserAndWishInfo(user2, userEndTimeWishInfo));

        if (subject != null) {
            userStartTimeOnSubjWishInfo = wishInfoService.getWishInfoByType(WishTypeEnum.USER_START_TIME_ON_SUBJ);
            userEndTimeOnSubjWishInfo = wishInfoService.getWishInfoByType(WishTypeEnum.USER_END_TIME_ON_SUBJ);
            user1Wishes.addAll(wishRepository.findWishEntitiesByFromUserAndWishInfo(user1, userEndTimeOnSubjWishInfo));
            user1Wishes.addAll(wishRepository.findWishEntitiesByFromUserAndWishInfo(user1, userStartTimeOnSubjWishInfo));
            user2Wishes.addAll(wishRepository.findWishEntitiesByFromUserAndWishInfo(user2, userEndTimeOnSubjWishInfo));
            user2Wishes.addAll(wishRepository.findWishEntitiesByFromUserAndWishInfo(user2, userStartTimeOnSubjWishInfo));
        }
        int point = 0;
        for (WishEntity user1Wish : user1Wishes) {
            for (WishEntity user2Wish : user2Wishes) {
                if (user1Wish.similarWishes(user2Wish)) {
                    point += 2;
                } else {
                    if (user1Wish.getWeekDay() == null && user2Wish.getWeekDay() == null || user1Wish.getWeekDay().equals(user2Wish.getWeekDay())) {
                        switch (user1Wish.getWishInfo().getType()) {
                            case "USER_START_TIME":
                            case "USER_START_TIME_ON_SUBJ":
                                point += getPointForTwoTimeValues(user1Wish.getPairStNum(), user2Wish.getPairStNum());
                            case "USER_END_TIME":
                            case "USER_END_TIME_ON_SUBJ":
                                point += getPointForTwoTimeValues(user1Wish.getPairEndNum(), user2Wish.getPairEndNum());
                        }
                    }
                }
            }
        }
        return point;
    }

    private int getPointForTwoTimeValues(int user1Time, int user2Time) {
        if (user1Time == user2Time)
            return 2;
        if (Math.abs(user1Time - user2Time) <= 2)
            return 1;
        if (Math.abs(user1Time - user2Time) >= 5)
            return -2;
        return -1;
    }

    @Override
    public void generateTeachToStudForSubjectWishes() {
    }

    @Override
    public void generateTeachToSubjAudWishes() {
        List<WishEntity> wishes = new ArrayList<>();

        for (UserEntity teacher : userService.getAllTeachers()) {
            for (SubjectEntity subject : userSubjService.getAllSubjectsByTeacher(teacher)) {
                List<EquipmentEntity> teacherRequiredEquipmentForSubject = getTeacherRequiredEquipmentForSubject(teacher, subject);
                List<AuditoryEntity> auditories = audEquipService.getAllAuditoriesByListOfEquipment(teacherRequiredEquipmentForSubject);
                auditories.forEach(auditory -> {
                    WishEntity wish = new WishEntity();
                    wish.setSubject(subject);
                    wish.setTeachUser(teacher);
                    wish.setAuditory(auditory);
                    wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.AUTO));
                    wish.setWishInfo(wishInfoService.getWishInfoByType(WishTypeEnum.TEACH_SUBJ_AUD));
                    wishes.add(wish);
                });
            }
        }
        wishRepository.save(wishes);
    }

    @Override
    public List<EquipmentEntity> getTeacherRequiredEquipmentForSubject(UserEntity teacher, SubjectEntity subject) {
        WishInfoEntity teachToSubjEquipWishInfo =
                wishInfoService.getWishInfoByType(WishTypeEnum.TEACH_SUBJ_AUD_EQUIP);
        List<WishEntity> wishEntities =
                wishRepository.findWishEntitiesByTeachUserAndSubjectAndWishInfo(teacher, subject, teachToSubjEquipWishInfo);
        List<EquipmentEntity> teacherRequiredEquipmentForSubject = new ArrayList<>();
        wishEntities.forEach(wishEntity ->
                teacherRequiredEquipmentForSubject.add(wishEntity.getEquipment())
        );
        return teacherRequiredEquipmentForSubject;
    }

    @Override
    public List<EquipmentEntity> getRequiredEquipmentForSubject(SubjectEntity subject) {
        WishInfoEntity subjectToEquipmentWishInfo =
                wishInfoService.getWishInfoByType(WishTypeEnum.SUBJ_EQUIP);
        List<WishEntity> wishEntities =
                wishRepository.findWishEntitiesBySubjectAndWishInfo(subject, subjectToEquipmentWishInfo);
        List<EquipmentEntity> requiredEquipmentForSubject = new ArrayList<>();
        wishEntities.forEach(wishEntity ->
                requiredEquipmentForSubject.add(wishEntity.getEquipment())
        );
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
