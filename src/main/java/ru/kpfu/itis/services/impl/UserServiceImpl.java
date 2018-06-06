package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.IupSubjEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.repositories.UserRepository;
import ru.kpfu.itis.services.IupService;
import ru.kpfu.itis.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    IupService iupService;

    @Override
    public UserEntity getBySurname(String surname) {
        return userRepository.findBySurname(surname);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserEntity> getUsersBySubjectInIup(SubjectEntity subject) {
        List<IupSubjEntity> iupSubjects = iupService.getIupSubjectsBySubject(subject);
        List<UserEntity> users = new ArrayList<>();
        iupSubjects.forEach(iupSubject -> users.add(iupSubject.getUser()));
        return users;
    }
}
