package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.InterestEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.repositories.InterestRepository;
import ru.kpfu.itis.services.InterestService;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterestServiceImpl implements InterestService {

    @Autowired
    InterestRepository interestRepository;

    @Override
    public List<InterestEntity> getInterestsByUser(UserEntity user) {
        List<InterestEntity> interests = new ArrayList<>();
        if (user.getUserInterests() == null || user.getUserInterests().size() == 0)
            return null;
        user.getUserInterests().forEach(userInterest -> interests.add(userInterest.getInterest()));
        return interests;
    }
}