package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.InterestEntity;

@Repository
public interface InterestRepository extends JpaRepository<InterestEntity, Long> {

    InterestEntity findByName(String name);

}