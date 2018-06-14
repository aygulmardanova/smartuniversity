package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.entity.UserRoleEntity;
import ru.kpfu.itis.entity.enums.UserRoleEnum;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findBySurname(String surname);

    List<UserEntity> findAllByUserRole(UserRoleEntity userRole);

    List<UserEntity> findAllByOrderBySurnameAsc();

}
