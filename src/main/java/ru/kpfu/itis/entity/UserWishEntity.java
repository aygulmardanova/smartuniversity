package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_wish", schema = "uni", catalog = "smartuniversity")
public class UserWishEntity extends IdObject<Long> {

    @Basic
    @Column(name = "week_day", nullable = true)
    private Integer weekDay;

    @Basic
    @Column(name = "pair_st_num", nullable = true)
    private Integer pairStNum;

    @Basic
    @Column(name = "pair_end_num", nullable = true)
    private Integer pairEndNum;

    @ManyToOne
    @JoinColumn(name = "from_id", referencedColumnName = "id")
    private UserEntity fromUser;

    @ManyToOne
    @JoinColumn(name = "stud_id", referencedColumnName = "id")
    private UserEntity studUser;

    @ManyToOne
    @JoinColumn(name = "teach_id", referencedColumnName = "id")
    private UserEntity teachUser;

    @ManyToOne
    @JoinColumn(name = "subj_id", referencedColumnName = "id")
    private SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name = "aud_id", referencedColumnName = "id")
    private AuditoryEntity auditory;

    @ManyToOne
    @JoinColumn(name = "wish_id", referencedColumnName = "id")
    private WishEntity wish;

    @ManyToOne
    @JoinColumn(name = "equip_id", referencedColumnName = "id")
    private EquipmentEntity equipment;

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public Integer getPairStNum() {
        return pairStNum;
    }

    public void setPairStNum(Integer pairStNum) {
        this.pairStNum = pairStNum;
    }

    public Integer getPairEndNum() {
        return pairEndNum;
    }

    public void setPairEndNum(Integer pairEndNum) {
        this.pairEndNum = pairEndNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserWishEntity that = (UserWishEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(weekDay, that.weekDay) &&
                Objects.equals(pairStNum, that.pairStNum) &&
                Objects.equals(pairEndNum, that.pairEndNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), weekDay, pairStNum, pairEndNum);
    }

    public UserEntity getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserEntity fromUser) {
        this.fromUser = fromUser;
    }

    public UserEntity getStudUser() {
        return studUser;
    }

    public void setStudUser(UserEntity studUser) {
        this.studUser = studUser;
    }

    public UserEntity getTeachUser() {
        return teachUser;
    }

    public void setTeachUser(UserEntity teachUser) {
        this.teachUser = teachUser;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }

    public AuditoryEntity getAuditory() {
        return auditory;
    }

    public void setAuditory(AuditoryEntity auditory) {
        this.auditory = auditory;
    }

    public WishEntity getWish() {
        return wish;
    }

    public void setWish(WishEntity wish) {
        this.wish = wish;
    }

    public EquipmentEntity getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentEntity equipment) {
        this.equipment = equipment;
    }
}
