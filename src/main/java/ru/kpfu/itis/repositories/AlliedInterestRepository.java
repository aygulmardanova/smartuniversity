package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.AlliedInterestEntity;
import ru.kpfu.itis.entity.InterestEntity;

@Repository
public interface AlliedInterestRepository extends JpaRepository<AlliedInterestEntity, Long> {

    AlliedInterestEntity findByInterestAndAlliedInterest(InterestEntity interest, InterestEntity alliedInterest);

}