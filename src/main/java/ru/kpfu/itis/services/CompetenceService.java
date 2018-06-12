package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.AuditoryEntity;
import ru.kpfu.itis.entity.CompetenceEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;

import java.util.List;

public interface CompetenceService {

    List<CompetenceEntity> getCompetencesByUser(UserEntity user);

    List<CompetenceEntity> getStudentIupCompetencesByUser(UserEntity user);

    List<CompetenceEntity> getCompetencesBySubject(SubjectEntity subject);

    List<CompetenceEntity> getCompetencesByAuditory(AuditoryEntity auditory);

}