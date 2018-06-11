package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.UserRoleEntity;
import ru.kpfu.itis.entity.enums.UserRoleEnum;

public interface UserRoleService {

    UserRoleEntity getByCode(UserRoleEnum userRole);

}
