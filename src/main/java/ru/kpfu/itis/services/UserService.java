package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.UserEntity;

public interface UserService {

    UserEntity findBySurname(String surname);

}
