package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.IupSubjEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.repositories.IupRepository;
import ru.kpfu.itis.services.IupService;

import java.util.List;

@Service
public class IupServiceImpl implements IupService {

    @Autowired
    IupRepository iupRepository;

    @Override
    public void saveIupSubject(IupSubjEntity iupSubject) {
        iupRepository.save(iupSubject);
    }

    @Override
    public List<IupSubjEntity> getIupSubjectsBySubject(SubjectEntity subject) {
        return iupRepository.findAllBySubject(subject);
    }

    @Override
    public List<IupSubjEntity> getIupSubjectsByUser(UserEntity user) {
        return iupRepository.findAllByUser(user);
    }
}
