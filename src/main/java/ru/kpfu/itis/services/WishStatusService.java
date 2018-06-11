package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.WishStatusEntity;
import ru.kpfu.itis.entity.enums.WishStatusEnum;

public interface WishStatusService {

    WishStatusEntity getWishStatusByName(WishStatusEnum name);

}
