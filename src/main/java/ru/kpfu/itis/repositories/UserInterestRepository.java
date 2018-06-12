package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.UserInterestEntity;

@Repository
public interface UserInterestRepository extends JpaRepository<UserInterestEntity, Long> {

}