package ru.kpfu.itis.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "uni", name = "user", catalog = "smartuniversity")
public class UserEntity extends IdObject<Long> {

    @Basic
    @Column(name = "surname", nullable = true, length = -1)
    private String surname;

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;

    @Basic
    @Column(name = "patronymic", nullable = true, length = -1)
    private String patronymic;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<IupSubjEntity> iupSubjs;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private UserRoleEntity userRole;

    @OneToMany(mappedBy = "userBy", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserCompEntity> userComps;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<UserInterestEntity> userInterests;

    @OneToMany(mappedBy = "fromUser", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<WishEntity> userWishes;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(patronymic, that.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), surname, name, patronymic);
    }

    public List<IupSubjEntity> getIupSubjs() {
        return iupSubjs;
    }

    public void setIupSubjs(List<IupSubjEntity> iupSubjs) {
        this.iupSubjs = iupSubjs;
    }

    public UserRoleEntity getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleEntity userRole) {
        this.userRole = userRole;
    }

    public List<UserCompEntity> getUserComps() {
        return userComps;
    }

    public void setUserComps(List<UserCompEntity> userComps) {
        this.userComps = userComps;
    }

    public List<UserInterestEntity> getUserInterests() {
        return userInterests;
    }

    public void setUserInterests(List<UserInterestEntity> userInterests) {
        this.userInterests = userInterests;
    }

    public List<WishEntity> getUserWishes() {
        return userWishes;
    }

    public void setUserWishes(List<WishEntity> userWishes) {
        this.userWishes = userWishes;
    }
}