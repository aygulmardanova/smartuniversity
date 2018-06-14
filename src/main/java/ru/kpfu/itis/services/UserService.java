package ru.kpfu.itis.services;

import ru.kpfu.itis.entity.SubjectEntity;
import ru.kpfu.itis.entity.UserEntity;
import ru.kpfu.itis.entity.enums.UserRoleEnum;

import java.util.List;

public interface UserService {

    UserEntity getById(Long id);

    UserEntity getBySurname(String surname);

    List<UserEntity> getAllUsers();

    List<UserEntity> getAllStudents();

    List<UserEntity> getAllStudentsOrderBySurname();

    List<UserEntity> getAllStudentsOrderBySimilarity(UserEntity student);

    List<UserEntity> getAllTeachers();

    List<UserEntity> getAllTeachersOrderBySurname();

    List<UserEntity> getAllUsersByUserRole(UserRoleEnum userRole);

    List<UserEntity> getAllUsersByUserRoleOrderBySurname(UserRoleEnum userRole);

    List<UserEntity> getUsersBySubjectInIup(SubjectEntity subject);

}
