package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;

import java.util.List;

public interface SubjectService {

    List<SubjectEntity> getAllSubjects();

    SubjectEntity getByName(String name);

    SubjectEntity getById(Long id);

    List<SubjectEntity> getSubjectsByStudentFromIup(UserEntity student);
}
