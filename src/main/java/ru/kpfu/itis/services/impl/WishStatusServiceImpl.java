package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.WishStatusEntity;
import ru.kpfu.itis.entity.enums.WishStatusEnum;
import ru.kpfu.itis.repositories.WishStatusRepository;
import ru.kpfu.itis.services.WishStatusService;

@Service
public class WishStatusServiceImpl implements WishStatusService {

    @Autowired
    WishStatusRepository wishStatusRepository;

    @Override
    public WishStatusEntity getWishStatusByName(WishStatusEnum name) {
        return wishStatusRepository.findByName(name.name());
    }
}
