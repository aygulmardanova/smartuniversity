package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.entity.UserSubjEntity;

import java.util.List;

public interface UserSubjService {

    List<UserSubjEntity> getAllBySubject(SubjectEntity subject);

    List<UserSubjEntity> getAllByTeacher(UserEntity teacher);

    List<UserEntity> getAllTeachersByUserSubjList(List<UserSubjEntity> userSubjs);

    List<SubjectEntity> getAllSubjectsByUserSubjList(List<UserSubjEntity> userSubjs);

    List<UserEntity> getAllTeachersBySubject(SubjectEntity subject);

    List<SubjectEntity> getAllSubjectsByTeacher(UserEntity teacher);

}