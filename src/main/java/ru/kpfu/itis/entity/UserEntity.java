package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(schema = "uni", name = "user", catalog = "smartuniversity")
public class UserEntity extends IdObject<Long> {

    private String surname;
    private String name;
    private String patronym;
    private Integer id;
    private String patronymic;
    private Collection<IupSubjEntity> iupSubjs;
    private UserRoleEntity userRole;
    private Collection<UserCompEntity> userComps;
    private Collection<UserInterestEntity> userInterests;
    private Collection<UserWishEntity> userWishes;

    @Basic
    @Column(name = "surname", nullable = true, length = -1)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "patronymic", nullable = true, length = -1)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(id, that.id) &&
                Objects.equals(patronymic, that.patronymic);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, surname, name, patronymic);
    }

    @OneToMany(mappedBy = "user")
    public Collection<IupSubjEntity> getIupSubjs() {
        return iupSubjs;
    }

    public void setIupSubjs(Collection<IupSubjEntity> iupSubjs) {
        this.iupSubjs = iupSubjs;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    public UserRoleEntity getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEntity userRole) {
        this.userRole = userRole;
    }

    @OneToMany(mappedBy = "userBy")
    public Collection<UserCompEntity> getUserComps() {
        return userComps;
    }

    public void setUserComps(Collection<UserCompEntity> userComps) {
        this.userComps = userComps;
    }

    @OneToMany(mappedBy = "user")
    public Collection<UserInterestEntity> getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(Collection<UserInterestEntity> userInterests) {
        this.userInterests = userInterests;
    }

    @OneToMany(mappedBy = "fromUser")
    public Collection<UserWishEntity> getUserWishes() {
        return userWishes;
    }

    public void setUserWishes(Collection<UserWishEntity> userWishes) {
        this.userWishes = userWishes;
    }
}