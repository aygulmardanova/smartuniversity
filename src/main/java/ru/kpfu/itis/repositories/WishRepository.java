package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.WishEntity;

@Repository
public interface WishRepository extends JpaRepository<WishEntity, Long> {


}
