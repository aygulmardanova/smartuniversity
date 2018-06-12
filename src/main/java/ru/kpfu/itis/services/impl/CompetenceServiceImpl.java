package ru.kpfu.itis.services.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.CompetenceEntity;
import ru.kpfu.itis.entity.IupSubjEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.services.CompetenceService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompetenceServiceImpl implements CompetenceService {

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

}