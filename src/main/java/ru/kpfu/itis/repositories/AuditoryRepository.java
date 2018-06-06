package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.AuditoryEntity;

@Repository
public interface AuditoryRepository extends JpaRepository<AuditoryEntity, Long> {

    AuditoryEntity findFirstByName(String name);

}
