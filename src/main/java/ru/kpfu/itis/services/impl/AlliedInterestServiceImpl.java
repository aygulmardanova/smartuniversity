package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.InterestEntity;
import ru.kpfu.itis.repositories.AlliedInterestRepository;
import ru.kpfu.itis.services.AlliedInterestService;

@Service
public class AlliedInterestServiceImpl implements AlliedInterestService {

    @Autowired
    AlliedInterestRepository alliedInterestRepository;

    @Override
    public Boolean isAlliedInterests(InterestEntity interest1, InterestEntity interest2) {
        return alliedInterestRepository.findByInterestAndAlliedInterest(interest1, interest2) != null
                || alliedInterestRepository.findByInterestAndAlliedInterest(interest2, interest1) != null;
    }

}