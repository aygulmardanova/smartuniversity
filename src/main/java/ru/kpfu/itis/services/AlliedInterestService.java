package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.InterestEntity;

public interface AlliedInterestService {

    Boolean isAlliedInterests(InterestEntity interest1, InterestEntity interest2);

}