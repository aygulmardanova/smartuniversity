package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.IupSubjEntity;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;

import java.util.List;

@Repository
public interface IupRepository extends JpaRepository<IupSubjEntity, Long> {

    List<IupSubjEntity> findAllBySubject(SubjectEntity subject);

    List<IupSubjEntity> findAllByUser(UserEntity user);
}
