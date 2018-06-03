package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity getBySurname(String surname);

    List<UserEntity> getAllUsers();

}
