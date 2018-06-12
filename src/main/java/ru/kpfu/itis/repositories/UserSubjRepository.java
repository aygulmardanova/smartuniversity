package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.entity.UserSubjEntity;

import java.util.List;

@Repository
public interface UserSubjRepository extends JpaRepository<UserSubjEntity, Long> {

    List<UserSubjEntity> findAllBySubject(SubjectEntity subject);

    List<UserSubjEntity> findAllByTeacher(UserEntity teacher);

}