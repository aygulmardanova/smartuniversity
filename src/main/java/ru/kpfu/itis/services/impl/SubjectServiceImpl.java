package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.repositories.SubjectRepository;
import ru.kpfu.itis.services.SubjectService;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;


    @Override
    public List<SubjectEntity> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
