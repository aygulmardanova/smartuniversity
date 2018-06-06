package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.IupSubjEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;

import java.util.List;

public interface IupService {

    void saveIupSubject(IupSubjEntity iupSubject);

    List<IupSubjEntity> getIupSubjectsBySubject(SubjectEntity subject);

    List<IupSubjEntity> getIupSubjectsByUser(UserEntity user);

}
