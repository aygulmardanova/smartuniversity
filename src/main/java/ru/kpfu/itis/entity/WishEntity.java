package ru.kpfu.itis.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "wish", schema = "uni", catalog = "smartuniversity")
public class WishEntity extends IdObject<Long> {

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
    @JoinColumn(name = "wish_info_id", referencedColumnName = "id")
    private WishInfoEntity wishInfo;

    @ManyToOne
    @JoinColumn(name = "wish_status_id", referencedColumnName = "id")
    private WishStatusEntity wishStatus;

    @ManyToOne
    @JoinColumn(name = "equip_id", referencedColumnName = "id")
    private EquipmentEntity equipment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishEntity that = (WishEntity) o;
        switch (that.getWishInfo().getType()) {
            case "SUBJ_EQUIP":
                return Objects.equals(subject, that.subject) &&
                        Objects.equals(equipment, that.equipment);
            case "SUBJ_AUD":
                return Objects.equals(subject, that.subject) &&
                        Objects.equals(auditory, that.auditory);
            case "STUD_TO_STUD":
                return Objects.equals(subject, that.subject) &&
                        Objects.equals(auditory, that.auditory);
            case "STUD_TO_STUD_ON_SUBJ":
                return Objects.equals(subject, that.subject) &&
                        Objects.equals(auditory, that.auditory);
            case "STUD_TO_TEACH":
                return Objects.equals(subject, that.subject) &&
                        Objects.equals(auditory, that.auditory);
            case "STUD_TO_TEACH_ON_SUBJ":
                return Objects.equals(subject, that.subject) &&
                        Objects.equals(auditory, that.auditory);
            case "TEACH_TO_STUD":
                return Objects.equals(subject, that.subject) &&
                        Objects.equals(auditory, that.auditory);
            case "TEACH_TO_STUD_ON_SUBJ":
                return Objects.equals(subject, that.subject) &&
                        Objects.equals(auditory, that.auditory);
            case "USER_START_TIME":
                return Objects.equals(fromUser, that.fromUser) &&
                        Objects.equals(pairStNum, that.pairStNum);
            case "USER_START_TIME_ON_SUBJ":
                return Objects.equals(fromUser, that.fromUser) &&
                        Objects.equals(pairStNum, that.pairStNum) &&
                        Objects.equals(subject, that.subject);
            case "USER_END_TIME":
                return Objects.equals(fromUser, that.fromUser) &&
                        Objects.equals(pairEndNum, that.pairEndNum);
            case "USER_END_TIME_ON_SUBJ":
                return Objects.equals(fromUser, that.fromUser) &&
                        Objects.equals(pairEndNum, that.pairEndNum) &&
                        Objects.equals(subject, that.subject);
            case "TEACH_SUBJ_AUD":
                return Objects.equals(teachUser, that.teachUser) &&
                        Objects.equals(subject, that.subject) &&
                        Objects.equals(auditory, that.auditory);
            case "TEACH_SUBJ_AUD_EQUIP":
                return Objects.equals(teachUser, that.teachUser) &&
                        Objects.equals(subject, that.subject) &&
                        Objects.equals(equipment, that.equipment);

        }
        return Objects.equals(weekDay, that.weekDay) &&
                Objects.equals(pairStNum, that.pairStNum) &&
                Objects.equals(pairEndNum, that.pairEndNum) &&
                Objects.equals(fromUser, that.fromUser) &&
                Objects.equals(studUser, that.studUser) &&
                Objects.equals(teachUser, that.teachUser) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(auditory, that.auditory) &&
                Objects.equals(wishInfo, that.wishInfo) &&
                Objects.equals(wishStatus, that.wishStatus) &&
                Objects.equals(equipment, that.equipment);
    }

    public Boolean similarWishes(WishEntity wish) {
        if (wish == null || !wishInfo.getType().equals(wish.getWishInfo().getType()))
            return false;
        if (weekDay != null && wish.weekDay != null && !weekDay.equals(wish.weekDay))
            return false;
        switch (wishInfo.getType()) {
            case "USER_START_TIME":
                return Objects.equals(pairStNum, wish.pairStNum);
            case "USER_START_TIME_ON_SUBJ":
                return Objects.equals(pairStNum, wish.pairStNum) &&
                        Objects.equals(subject, wish.subject);
            case "USER_END_TIME":
                return Objects.equals(pairEndNum, wish.pairEndNum);
            case "USER_END_TIME_ON_SUBJ":
                return Objects.equals(pairEndNum, wish.pairEndNum) &&
                        Objects.equals(subject, wish.subject);
        }
        return false;
    }

    @Override
    public int hashCode() {

        return Objects.hash(weekDay, pairStNum, pairEndNum, fromUser, studUser, teachUser, subject, auditory, wishInfo, wishStatus, equipment);
    }
}
