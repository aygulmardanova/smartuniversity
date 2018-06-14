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
    IupService iupService;

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
    public void saveTimeWish(Long fromUserId, Integer pairStNum, Integer pairEndNum, Integer weekDay, Long subjectId) {
        WishTypeEnum wishType;
        WishEntity wish = new WishEntity();
        if (pairStNum != null) {
            wish.setPairStNum(pairStNum);
            if (subjectId != null) {
                wishType = WishTypeEnum.USER_START_TIME_ON_SUBJ;
            } else {
                wishType = WishTypeEnum.USER_START_TIME;
            }
        } else if (pairEndNum != null) {
            wish.setPairEndNum(pairEndNum);
            if (subjectId != null) {
                wishType = WishTypeEnum.USER_END_TIME_ON_SUBJ;
            } else {
                wishType = WishTypeEnum.USER_END_TIME;
            }
        } else
            return;

        wish.setFromUser(userService.getById(fromUserId));
        wish.setWishInfo(wishInfoService.getWishInfoByType(wishType));
        if (weekDay != null)
            wish.setWeekDay(weekDay);
        if (subjectId != null)
            wish.setSubject(subjectService.getById(subjectId));
        wishRepository.save(wish);
    }

    @Override
    public List<WishEntity> getWishesForUser(UserEntity user) {
        return wishRepository.findWishEntitiesByFromUser(user);
    }

    @Override
    public void generateWishes() {
        generateSubjectToAudWishes();
        generateTeachToSubjAudWishes();
        generateStudToStudWishes();
        generateStudToStudForSubjectWishes();
        generateTeachToStudWishes();
        generateTeachToStudForSubjectWishes();
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

                if (similarityCount > 0)
                    similarityWithStud.put(students.get(j), similarityCount);
            }
            Map<UserEntity, Integer> similarityWithStudSorted = getSortedByValueSimilarityMap(similarityWithStud);
            double average = similarityWithStudSorted.values().stream().mapToInt(Integer::intValue).sum() / similarityWithStudSorted.size();
            int studFrom = i;
            similarityWithStudSorted.keySet().stream()
                    .filter(stud -> similarityWithStudSorted.get(stud) >= average)
                    .forEach(stud -> {
                        WishEntity wish = new WishEntity();
                        wish.setFromUser(students.get(studFrom));
                        wish.setStudUser(stud);
                        wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.AUTO));
                        wish.setWishInfo(wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_STUD));
                        wishes.add(wish);
                    });
        }
        wishRepository.save(wishes);
    }

    @Override
    public int getPointForStudCompetences(UserEntity stud1, UserEntity stud2) {
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

    @Override
    public Map<UserEntity, Integer> getSortedByValueSimilarityMap(Map<UserEntity, Integer> similarity) {
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
        List<WishEntity> wishes = new ArrayList<>();
        List<UserEntity> students = userService.getAllStudents();
        for (int i = 0; i < students.size() - 1; i++) {
            List<IupSubjEntity> iupSubjForStud = iupService.getIupSubjectsByStudent(students.get(i));
            int fromStudI = i;
            iupSubjForStud.forEach(iupSubj -> {

                Map<UserEntity, Integer> similarityWithStud = new HashMap<>();
                for (int j = fromStudI + 1; j < students.size(); j++) {
                    int similarityCount = 0;
                    similarityCount += getPointForTimeWishes(students.get(fromStudI), students.get(j), iupSubj.getSubject());
                    similarityCount += getPointForInterests(students.get(fromStudI), students.get(j));
                    similarityCount += getPointForStudCompetences(students.get(fromStudI), students.get(j));

                    List<SubjectEntity> stud1Subjects = iupService.getSubjectsByStudent(students.get(fromStudI));
                    List<SubjectEntity> stud2Subjects = iupService.getSubjectsByStudent(students.get(j));
                    similarityCount += stud1Subjects.stream().filter(stud2Subjects::contains).count();

                    if (similarityCount > 0)
                        similarityWithStud.put(students.get(j), similarityCount);
                }
                Map<UserEntity, Integer> similarityWithStudSorted = getSortedByValueSimilarityMap(similarityWithStud);
                double average = similarityWithStudSorted.values().stream().mapToInt(Integer::intValue).sum() / similarityWithStudSorted.size();
                similarityWithStudSorted.keySet().stream()
                        .filter(stud -> similarityWithStudSorted.get(stud) >= average)
                        .forEach(stud -> {
                            WishEntity wish = new WishEntity();
                            wish.setFromUser(students.get(fromStudI));
                            wish.setStudUser(stud);
                            wish.setSubject(iupSubj.getSubject());
                            wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.AUTO));
                            wish.setWishInfo(wishInfoService.getWishInfoByType(WishTypeEnum.STUD_TO_STUD_ON_SUBJ));
                            wishes.add(wish);
                        });
            });
        }
        wishRepository.save(wishes);
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

                if (similarityCount > 0)
                    similarity.put(new AbstractMap.SimpleEntry<>(teacher, student), similarityCount);
            }
        }
        Map<AbstractMap.SimpleEntry<UserEntity, UserEntity>, Integer> similaritySorted = getSortedBySimilarityMap(similarity);
        double average = similaritySorted.values().stream().mapToInt(Integer::intValue).sum() / similaritySorted.size();
        List<WishEntity> wishes = new ArrayList<>();
        similaritySorted.keySet().forEach(keyPair -> {
            if (similaritySorted.get(keyPair) >= average) {
                WishEntity wish = new WishEntity();
                wish.setFromUser(keyPair.getKey());
                wish.setStudUser(keyPair.getValue());
                wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.AUTO));
                wish.setWishInfo(wishInfoService.getWishInfoByType(WishTypeEnum.TEACH_TO_STUD));
                wishes.add(wish);
            }
        });
        wishRepository.save(wishes);
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

    @Override
    public Integer getPointForTeachSubjCompetences(UserEntity teacher, SubjectEntity subject) {
        int point = 0;
        List<CompetenceEntity> teacherCompetences = competenceService.getCompetencesByUser(teacher);
        List<CompetenceEntity> subjectCompetences = competenceService.getCompetencesBySubject(subject);
        for (CompetenceEntity subjectCompetence : subjectCompetences) {
            if (teacherCompetences.contains(subjectCompetence))
                point++;
        }
        return point;
    }

    @Override
    public Integer getPointForInterests(UserEntity user1, UserEntity user2) {
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

    @Override
    public Integer getPointForTimeWishes(UserEntity user1, UserEntity user2, SubjectEntity subject) {
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

    @Override
    public int getPointForTwoTimeValues(int user1Time, int user2Time) {
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
        List<WishEntity> wishes = new ArrayList<>();
        List<UserEntity> teachers = userService.getAllTeachers();
        List<UserEntity> students = userService.getAllStudents();
        for (UserEntity teacher : teachers) {
            List<SubjectEntity> teacherSubjects = userSubjService.getAllSubjectsByTeacher(teacher);
            teacherSubjects.forEach(teacherSubj -> {

                Map<UserEntity, Integer> similarityWithStudForSubj = new HashMap<>();
                for (UserEntity student : students) {
                    List<SubjectEntity> studentSubjects = iupService.getSubjectsByStudent(student);
                    if (studentSubjects.contains(teacherSubj)) {
                        int similarityCount = 0;
                        similarityCount += getPointForTimeWishes(teacher, student, teacherSubj);
                        similarityCount += getPointForInterests(teacher, student);
                        similarityCount += getPointForTeachSubjCompetences(teacher, teacherSubj);

                        if (similarityCount > 0)
                            similarityWithStudForSubj.put(student, similarityCount);
                    }
                }
                Map<UserEntity, Integer> similarityWithStudForSubjSorted = getSortedByValueSimilarityMap(similarityWithStudForSubj);
                double average = similarityWithStudForSubjSorted.values().stream().mapToInt(Integer::intValue).sum() / similarityWithStudForSubjSorted.size();
                similarityWithStudForSubjSorted.keySet().stream()
                        .filter(stud -> similarityWithStudForSubjSorted.get(stud) >= average)
                        .forEach(stud -> {
                            WishEntity wish = new WishEntity();
                            wish.setFromUser(teacher);
                            wish.setStudUser(stud);
                            wish.setSubject(teacherSubj);
                            wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.AUTO));
                            wish.setWishInfo(wishInfoService.getWishInfoByType(WishTypeEnum.TEACH_TO_STUD_ON_SUBJ));
                            wishes.add(wish);
                        });
            });
        }
        wishRepository.save(wishes);
    }

    @Override
    public void generateTeachToSubjAudWishes() {
        List<WishEntity> wishes = new ArrayList<>();

        for (UserEntity teacher : userService.getAllTeachers()) {
            for (SubjectEntity subject : userSubjService.getAllSubjectsByTeacher(teacher)) {
                List<CompetenceEntity> subjectCompetences = competenceService.getCompetencesBySubject(subject);
                List<CompetenceEntity> teacherCompetences = competenceService.getCompetencesByUser(teacher);
                List<EquipmentEntity> teacherRequiredEquipmentForSubject = getTeacherRequiredEquipmentForSubject(teacher, subject);
                List<AuditoryEntity> auditories = audEquipService.getAllAuditoriesByListOfEquipment(teacherRequiredEquipmentForSubject);
                List<AuditoryEntity> auditoriesForSubject = audEquipService.getAllAuditoriesByListOfEquipment(getRequiredEquipmentForSubject(subject));
                Map<AuditoryEntity, Integer> similarityWithAud = new HashMap<>();
                auditories.stream().filter(auditoriesForSubject::contains).forEach(auditory -> {
                    int similarityCount;
                    List<CompetenceEntity> auditoryCompetences = competenceService.getCompetencesByAuditory(auditory);
                    similarityCount = (int) subjectCompetences.stream().filter(auditoryCompetences::contains).count();
                    similarityCount += subjectCompetences.stream().filter(auditoryCompetences::contains).filter(teacherCompetences::contains).count();
                    similarityWithAud.put(auditory, similarityCount);
                });
                Map<AuditoryEntity, Integer> similarityWithAudSorted = getSimilarityMapSortedByValue(similarityWithAud);
                double average = similarityWithAudSorted.values().stream().mapToInt(Integer::intValue).sum() / similarityWithAudSorted.size();

                similarityWithAudSorted.keySet().forEach(auditory -> {
                    if (average != 0
                            && similarityWithAudSorted.get(auditory) != 0
                            && similarityWithAudSorted.get(auditory) >= average) {
                        WishEntity wish = new WishEntity();
                        wish.setSubject(subject);
                        wish.setTeachUser(teacher);
                        wish.setAuditory(auditory);
                        wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.AUTO));
                        wish.setWishInfo(wishInfoService.getWishInfoByType(WishTypeEnum.TEACH_SUBJ_AUD));
                        wishes.add(wish);
                    }
                });
            }
        }
        wishRepository.save(wishes);
    }

    /*public void generateSubjectToAudWishes2() {
        List<WishEntity> wishes = new ArrayList<>();
        for (SubjectEntity subject : subjectService.getAllSubjects()) {
            List<CompetenceEntity> subjectCompetences = competenceService.getCompetencesBySubject(subject);
            List<EquipmentEntity> requiredEquipmentForSubject = getRequiredEquipmentForSubject(subject);
            List<AuditoryEntity> auditories = audEquipService.getAllAuditoriesByListOfEquipment(requiredEquipmentForSubject);
            Map<AuditoryEntity, Integer> similarityWithAud = new HashMap<>();
            auditories.forEach((AuditoryEntity auditory) -> {
                int similarityCount;
                List<CompetenceEntity> auditoryCompetences = competenceService.getCompetencesByAuditory(auditory);
                similarityCount = (int) subjectCompetences.stream().filter(auditoryCompetences::contains).count();
                similarityWithAud.put(auditory, similarityCount);
            });
            Map<AuditoryEntity, Integer> similarityWithAudSorted = getSimilarityMapSortedByValue(similarityWithAud);
            int maxSimilarity = similarityWithAudSorted.values().stream().findFirst().orElse(0);
            similarityWithAudSorted.keySet().forEach(auditory -> {
                if (maxSimilarity != 0
                        && similarityWithAudSorted.get(auditory) != 0
                        && similarityWithAudSorted.get(auditory) >= maxSimilarity / 2) {
                    WishEntity wish = new WishEntity();
                    wish.setSubject(subject);
                    wish.setAuditory(auditory);
                    wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.AUTO));
                    wish.setWishInfo(wishInfoService.getWishInfoByType(WishTypeEnum.SUBJ_AUD));
                    wishes.add(wish);
                }
            });
        }
        wishRepository.save(wishes);
    }*/

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
        List<WishEntity> wishes = new ArrayList<>();
        for (SubjectEntity subject : subjectService.getAllSubjects()) {
            List<EquipmentEntity> requiredEquipmentForSubject = getRequiredEquipmentForSubject(subject);
            List<AuditoryEntity> auditories = audEquipService.getAllAuditoriesByListOfEquipment(requiredEquipmentForSubject);
            auditories.forEach(auditory -> {
                WishEntity wish = new WishEntity();
                wish.setSubject(subject);
                wish.setAuditory(auditory);
                wish.setWishStatus(wishStatusService.getWishStatusByName(WishStatusEnum.AUTO));
                wish.setWishInfo(wishInfoService.getWishInfoByType(WishTypeEnum.SUBJ_AUD));
                wishes.add(wish);
            });
        }
        wishRepository.save(wishes);
    }

    private Map<AuditoryEntity, Integer> getSimilarityMapSortedByValue(Map<AuditoryEntity, Integer> similarity) {
        List<Map.Entry<AuditoryEntity, Integer>> list = new LinkedList<>(similarity.entrySet());
        list.sort(Comparator.comparing(Map.Entry::getValue));
        Collections.reverse(list);

        Map<AuditoryEntity, Integer> similaritySorted = new LinkedHashMap<>();
        for (Map.Entry<AuditoryEntity, Integer> entry : list) {
            similaritySorted.put(entry.getKey(), entry.getValue());
        }
        return similaritySorted;
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
