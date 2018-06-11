package ru.kpfu.itis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.entity.UserRoleEntity;
import ru.kpfu.itis.entity.enums.UserRoleEnum;
import ru.kpfu.itis.repositories.UserRoleRepository;
import ru.kpfu.itis.services.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserRoleEntity getByCode(UserRoleEnum userRole) {
        return userRoleRepository.findByCode(userRole.name());
    }
}
