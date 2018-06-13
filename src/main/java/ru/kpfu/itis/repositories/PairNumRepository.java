package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.PairNumEntity;

import java.util.List;

@Repository
public interface PairNumRepository extends JpaRepository<PairNumEntity, Long> {

    List<PairNumEntity> findAll();

}
