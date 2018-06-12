package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.entity.WishEntity;
import ru.kpfu.itis.entity.WishInfoEntity;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<WishEntity, Long> {

    List<WishEntity> findWishEntitiesByFromUserAndWishInfo(UserEntity user, WishInfoEntity wishInfo);

    List<WishEntity> findWishEntitiesByFromUser(UserEntity user);

    List<WishEntity> findWishEntitiesByWishInfo(WishInfoEntity wishInfo);

    List<WishEntity> findWishEntitiesBySubjectAndWishInfo(SubjectEntity subject, WishInfoEntity wishInfo);

    List<WishEntity> findWishEntitiesByTeachUserAndSubjectAndWishInfo(UserEntity teachUser, SubjectEntity subject, WishInfoEntity wishInfo);

}
