package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.WishInfoEntity;
import ru.kpfu.itis.entity.enums.WishTypeEnum;

public interface WishInfoService {

    WishInfoEntity getWishInfoByType(WishTypeEnum type);
}
