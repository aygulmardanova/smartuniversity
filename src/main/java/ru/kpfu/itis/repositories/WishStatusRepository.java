package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.WishStatusEntity;
import ru.kpfu.itis.entity.enums.WishStatusEnum;

@Repository
public interface WishStatusRepository extends JpaRepository<WishStatusEntity, Long> {

    WishStatusEntity findByName(WishStatusEnum name);
}
