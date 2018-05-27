package ru.kpfu.itis.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_wish", schema = "uni", catalog = "smartuniversity")
public class UserWishEntity {
    private Long id;
    private Integer weekDay;
    private Integer pairStNum;
    private Integer pairEndNum;
    private UserEntity fromUser;
    private UserEntity studUser;
    private UserEntity teachUser;
    private SubjectEntity subjectBySubjId;
    private AuditoryEntity auditoryByAudId;
    private WishEntity wish;
    private EquipmentEntity equipmentByEquipId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "week_day", nullable = true)
    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    @Basic
    @Column(name = "pair_st_num", nullable = true)
    public Integer getPairStNum() {
        return pairStNum;
    }

    public void setPairStNum(Integer pairStNum) {
        this.pairStNum = pairStNum;
    }

    @Basic
    @Column(name = "pair_end_num", nullable = true)
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
        return Objects.equals(id, that.id) &&
                Objects.equals(weekDay, that.weekDay) &&
                Objects.equals(pairStNum, that.pairStNum) &&
                Objects.equals(pairEndNum, that.pairEndNum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, weekDay, pairStNum, pairEndNum);
    }

    @ManyToOne
    @JoinColumn(name = "from_id", referencedColumnName = "id")
    public UserEntity getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserEntity fromUser) {
        this.fromUser = fromUser;
    }

    @ManyToOne
    @JoinColumn(name = "stud_id", referencedColumnName = "id")
    public UserEntity getStudUser() {
        return studUser;
    }

    public void setStudUser(UserEntity studUser) {
        this.studUser = studUser;
    }

    @ManyToOne
    @JoinColumn(name = "teach_id", referencedColumnName = "id")
    public UserEntity getTeachUser() {
        return teachUser;
    }

    public void setTeachUser(UserEntity teachUser) {
        this.teachUser = teachUser;
    }

    @ManyToOne
    @JoinColumn(name = "subj_id", referencedColumnName = "id")
    public SubjectEntity getSubjectBySubjId() {
        return subjectBySubjId;
    }

    public void setSubjectBySubjId(SubjectEntity subjectBySubjId) {
        this.subjectBySubjId = subjectBySubjId;
    }

    @ManyToOne
    @JoinColumn(name = "aud_id", referencedColumnName = "id")
    public AuditoryEntity getAuditoryByAudId() {
        return auditoryByAudId;
    }

    public void setAuditoryByAudId(AuditoryEntity auditoryByAudId) {
        this.auditoryByAudId = auditoryByAudId;
    }

    @ManyToOne
    @JoinColumn(name = "wish_id", referencedColumnName = "id")
    public WishEntity getWish() {
        return wish;
    }

    public void setWish(WishEntity wish) {
        this.wish = wish;
    }

    @ManyToOne
    @JoinColumn(name = "equip_id", referencedColumnName = "id")
    public EquipmentEntity getEquipmentByEquipId() {
        return equipmentByEquipId;
    }

    public void setEquipmentByEquipId(EquipmentEntity equipmentByEquipId) {
        this.equipmentByEquipId = equipmentByEquipId;
    }
}
