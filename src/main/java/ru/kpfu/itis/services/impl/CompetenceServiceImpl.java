package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.*;
import ru.kpfu.itis.services.CompetenceService;
import ru.kpfu.itis.services.SubjectService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompetenceServiceImpl implements CompetenceService {

    @Autowired
    SubjectService subjectService;

    @Override
    public List<CompetenceEntity> getCompetencesByUser(UserEntity user) {
        List<CompetenceEntity> competences = new ArrayList<>();
        if (user.getUserComps() == null || user.getUserComps().size() == 0)
            return null;
        user.getUserComps().forEach(userComp -> competences.add(userComp.getCompetence()));
        return competences;
    }

    @Override
    public List<CompetenceEntity> getStudentIupCompetencesByUser(UserEntity user) {
        Set<CompetenceEntity> competences = new HashSet<>();
        List<IupSubjEntity> iupSubjects = user.getIupSubjs();
        for (IupSubjEntity iupSubj : iupSubjects) {
            iupSubj.getSubject().getSubjComps().forEach(subjComp -> competences.add(subjComp.getCompetence()));
        }
        return new ArrayList<>(competences);
    }

    @Override
    public List<CompetenceEntity> getCompetencesBySubject(SubjectEntity subject) {
        List<CompetenceEntity> competences = new ArrayList<>();
        if (subject.getSubjComps() == null || subject.getSubjComps().size() == 0)
            return null;
        subject.getSubjComps().forEach(subjComp -> competences.add(subjComp.getCompetence()));
        return competences;
    }

    @Override
    public List<CompetenceEntity> getCompetencesByAuditory(AuditoryEntity auditory) {
        List<CompetenceEntity> competences = new ArrayList<>();
        if (auditory.getAudComps() == null || auditory.getAudComps().size() == 0)
            return null;
        auditory.getAudComps().forEach(audComp -> competences.add(audComp.getCompetence()));
        return competences;    }

}