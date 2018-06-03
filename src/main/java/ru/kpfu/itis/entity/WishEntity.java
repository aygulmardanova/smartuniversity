package ru.kpfu.itis.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "wish", schema = "uni", catalog = "smartuniversity")
public class WishEntity extends IdObject<Long> {

    @Basic
    @Column(name = "week_day", nullable = true)
    private @Getter @Setter Integer weekDay;

    @Basic
    @Column(name = "pair_st_num", nullable = true)
    private @Getter @Setter Integer pairStNum;

    @Basic
    @Column(name = "pair_end_num", nullable = true)
    private @Getter @Setter Integer pairEndNum;

    @ManyToOne
    @JoinColumn(name = "from_id", referencedColumnName = "id")
    private @Getter @Setter UserEntity fromUser;

    @ManyToOne
    @JoinColumn(name = "stud_id", referencedColumnName = "id")
    private @Getter @Setter UserEntity studUser;

    @ManyToOne
    @JoinColumn(name = "teach_id", referencedColumnName = "id")
    private @Getter @Setter UserEntity teachUser;

    @ManyToOne
    @JoinColumn(name = "subj_id", referencedColumnName = "id")
    private @Getter @Setter SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name = "aud_id", referencedColumnName = "id")
    private @Getter @Setter AuditoryEntity auditory;

    @ManyToOne
    @JoinColumn(name = "wish_info_id", referencedColumnName = "id")
    private @Getter @Setter WishInfoEntity wishInfo;

    @ManyToOne
    @JoinColumn(name = "equip_id", referencedColumnName = "id")
    private @Getter @Setter EquipmentEntity equipment;
}
