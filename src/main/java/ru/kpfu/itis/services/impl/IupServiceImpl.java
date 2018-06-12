package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.IupSubjEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.repositories.IupRepository;
import ru.kpfu.itis.services.IupService;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<IupSubjEntity> getIupSubjectsByStudent(UserEntity user) {
        return iupRepository.findAllByUser(user);
    }

    @Override
    public List<UserEntity> getStudentsByListOfIupSubjects(List<IupSubjEntity> iupSubjects) {
        return iupSubjects.stream().map(iupSubject -> iupSubject.getUser()).collect(Collectors.toList());
    }

    @Override
    public List<SubjectEntity> getSubjectsByListOfIupSubjects(List<IupSubjEntity> iupSubjects) {
        return iupSubjects.stream().map(iupSubject -> iupSubject.getSubject()).collect(Collectors.toList());
    }

    @Override
    public List<SubjectEntity> getSubjectsByStudent(UserEntity student) {
        List<IupSubjEntity> iupSubjEntities = getIupSubjectsByStudent(student);
        return getSubjectsByListOfIupSubjects(iupSubjEntities);
    }

    @Override
    public List<UserEntity> getStudentsBySubject(SubjectEntity subject) {
        List<IupSubjEntity> iupSubjEntities = getIupSubjectsBySubject(subject);
        return getStudentsByListOfIupSubjects(iupSubjEntities);
    }
}
