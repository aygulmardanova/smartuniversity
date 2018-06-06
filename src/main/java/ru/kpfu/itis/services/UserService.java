package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity getBySurname(String surname);

    List<UserEntity> getAllUsers();

    List<UserEntity> getUsersBySubjectInIup(SubjectEntity subject);

}
