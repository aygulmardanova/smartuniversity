package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.entity.UserSubjEntity;
import ru.kpfu.itis.repositories.UserSubjRepository;
import ru.kpfu.itis.services.UserService;
import ru.kpfu.itis.services.UserSubjService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSubjServiceImpl implements UserSubjService {

    @Autowired
    UserSubjRepository userSubjRepository;

    @Autowired
    UserService userService;

    @Override
    public List<UserSubjEntity> getAllBySubject(SubjectEntity subject) {
        return userSubjRepository.findAllBySubject(subject);
    }

    @Override
    public List<UserSubjEntity> getAllByTeacher(UserEntity teacher) {
        return userSubjRepository.findAllByTeacher(teacher);
    }

    @Override
    public List<UserEntity> getAllTeachersByUserSubjList(List<UserSubjEntity> userSubjs) {
        List<UserEntity> teachers = new ArrayList<>();
        userSubjs.forEach(userSubj -> teachers.add(userSubj.getTeacher()));
        return teachers;
    }

    @Override
    public List<SubjectEntity> getAllSubjectsByUserSubjList(List<UserSubjEntity> userSubjs) {
        List<SubjectEntity> subjects = new ArrayList<>();
        userSubjs.forEach(userSubj -> subjects.add(userSubj.getSubject()));
        return subjects;
    }

    @Override
    public List<UserEntity> getAllTeachersBySubject(SubjectEntity subject) {
        return getAllTeachersByUserSubjList(getAllBySubject(subject));
    }

    @Override
    public List<SubjectEntity> getAllSubjectsByTeacher(UserEntity teacher) {
        return getAllSubjectsByUserSubjList(getAllByTeacher(teacher));
    }
}
