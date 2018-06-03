package ru.kpfu.itis.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
