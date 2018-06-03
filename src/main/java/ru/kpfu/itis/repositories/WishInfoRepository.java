package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.WishInfoEntity;

@Repository
public interface WishInfoRepository extends JpaRepository<WishInfoEntity, Long> {

    WishInfoEntity findByType(String type);

}
