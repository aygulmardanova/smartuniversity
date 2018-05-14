package ru.kpfu.itis.entity.enums;

public enum WishTypeEnum {

    SYSTEM(1),
    AUTO(2),
    STUDENT(3),
//    URGENT(4),
    REQUIRED(4),
    TEACHER(5);

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