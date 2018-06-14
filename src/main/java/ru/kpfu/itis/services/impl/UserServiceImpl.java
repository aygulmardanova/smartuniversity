package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.IupSubjEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.entity.WishEntity;
import ru.kpfu.itis.entity.enums.UserRoleEnum;
import ru.kpfu.itis.entity.enums.WishStatusEnum;
import ru.kpfu.itis.entity.enums.WishTypeEnum;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.services.IupService;
import ru.kpfu.itis.services.UserRoleService;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.services.WishService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    WishService wishService;

    @Autowired
    IupService iupService;

    @Override
    public UserEntity getById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public UserEntity getBySurname(String surname) {
        return userRepository.findBySurname(surname);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> getAllStudents() {
        return getAllUsersByUserRole(UserRoleEnum.STUDENT);
    }

    @Override
    public List<UserEntity> getAllStudentsOrderBySurname() {
        return getAllUsersByUserRoleOrderBySurname(UserRoleEnum.STUDENT);
    }

    @Override
    public List<UserEntity> getAllStudentsOrderBySimilarity(UserEntity student) {
        List<UserEntity> students = getAllStudents();
        Map<UserEntity, Integer> similarityWithStud = new HashMap<>();
        for (UserEntity studentUser : students) {
            int similarityCount = 0;
            similarityCount += wishService.getPointForTimeWishes(student, studentUser, null);
            similarityCount += wishService.getPointForInterests(student, studentUser);
            similarityCount += wishService.getPointForStudCompetences(student, studentUser);

            similarityWithStud.put(studentUser, similarityCount);
        }
        Map<UserEntity, Integer> similarityWithStudSorted = wishService.getSortedByValueSimilarityMap(similarityWithStud);
        return new ArrayList<>(similarityWithStudSorted.keySet());
    }

    @Override
    public List<UserEntity> getAllTeachers() {
        return getAllUsersByUserRole(UserRoleEnum.TEACHER);
    }

    @Override
    public List<UserEntity> getAllTeachersOrderBySurname() {
        return getAllUsersByUserRoleOrderBySurname(UserRoleEnum.TEACHER);
    }

    @Override
    public List<UserEntity> getAllUsersByUserRole(UserRoleEnum userRole) {
        return userRepository.findAllByUserRole(userRoleService.getByCode(userRole));
    }

    @Override
    public List<UserEntity> getAllUsersByUserRoleOrderBySurname(UserRoleEnum userRole) {
        return userRepository.findAllByOrderBySurnameAsc().stream()
                .filter(user -> userRoleService.getByCode(userRole).equals(user.getUserRole()))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserEntity> getUsersBySubjectInIup(SubjectEntity subject) {
        List<IupSubjEntity> iupSubjects = iupService.getIupSubjectsBySubject(subject);
        List<UserEntity> users = new ArrayList<>();
        iupSubjects.forEach(iupSubject -> users.add(iupSubject.getUser()));
        return users;
    }
}
