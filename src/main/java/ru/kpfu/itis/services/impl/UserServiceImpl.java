package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.IupSubjEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.entity.enums.UserRoleEnum;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.services.IupService;
import ru.kpfu.itis.services.UserRoleService;
import ru.kpfu.itis.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleService userRoleService;

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
    public List<UserEntity> getAllTeachers() {
        return getAllUsersByUserRole(UserRoleEnum.TEACHER);
    }

    @Override
    public List<UserEntity> getAllUsersByUserRole(UserRoleEnum userRole) {
        return userRepository.findAllByUserRole(userRoleService.getByCode(userRole));
    }

    @Override
    public List<UserEntity> getUsersBySubjectInIup(SubjectEntity subject) {
        List<IupSubjEntity> iupSubjects = iupService.getIupSubjectsBySubject(subject);
        List<UserEntity> users = new ArrayList<>();
        iupSubjects.forEach(iupSubject -> users.add(iupSubject.getUser()));
        return users;
    }
}
