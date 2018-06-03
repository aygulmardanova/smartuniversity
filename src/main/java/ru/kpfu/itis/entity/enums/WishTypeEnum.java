package ru.kpfu.itis.entity.enums;

public enum WishTypeEnum {
    STUD_TO_STUD(1),
    STUD_TO_STUD_ON_SUBJ(2),
    STUD_TO_TEACH(3),
    STUD_TO_TEACH_ON_SUBJ(4),
    TEACH_TO_STUD(5),
    TEACH_TO_STUD_ON_SUBJ(6),
    USER_START_TIME(4),
    USER_START_TIME_ON_SUBJ(7),
    USER_END_TIME(8),
    USER_END_TIME_ON_SUBJ(9),
    TEACH_SUBJ_AUD(10),
    TEACH_SUBJ_AUD_EQUIP(11),
    SUBJ_EQUIP(12),
    SUBJ_AUD(13);

    private Integer id;

    WishTypeEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static WishTypeEnum valueOf(Integer id) {
        for (WishTypeEnum wishTypeEnum : WishTypeEnum.values()){
            if(wishTypeEnum.getId().equals(id)) {
                return wishTypeEnum;
            }
        }
        return null;
    }
}
