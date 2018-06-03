package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.WishInfoEntity;

public interface WishInfoService {

    WishInfoEntity getWishInfoByType(String type);
}
