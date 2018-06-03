package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.WishInfoEntity;
import ru.kpfu.itis.repositories.WishInfoRepository;
import ru.kpfu.itis.services.WishInfoService;

@Service
public class WishInfoServiceImpl implements WishInfoService {

    @Autowired
    WishInfoRepository wishInfoRepository;

    @Override
    public WishInfoEntity getWishInfoByType(String type) {
        return wishInfoRepository.;
    }
}
