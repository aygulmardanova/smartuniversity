package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.IupSubjEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.repositories.SubjectRepository;
import ru.kpfu.itis.services.IupService;
import ru.kpfu.itis.services.SubjectService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    IupService iupService;

    @Override
    public List<SubjectEntity> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public List<SubjectEntity> getSubjectsByUser(UserEntity user) {
        List<IupSubjEntity> iupSubjects = iupService.getIupSubjectsByStudent(user);
        List<SubjectEntity> subjects = new ArrayList<>();
        iupSubjects.forEach(iupSubject -> subjects.add(iupSubject.getSubject()));
        return subjects;
    }
}
