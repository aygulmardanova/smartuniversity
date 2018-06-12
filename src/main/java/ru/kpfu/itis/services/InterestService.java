package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.InterestEntity;
import ru.kpfu.itis.entity.UserEntity;

import java.util.List;

public interface InterestService {

    List<InterestEntity> getInterestsByUser(UserEntity user);

}