package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;

import java.util.List;

public interface SubjectService {

    List<SubjectEntity> getAllSubjects();

    SubjectEntity getByName(String name);

    List<SubjectEntity> getSubjectsByStudentFromIup(UserEntity student);
}
