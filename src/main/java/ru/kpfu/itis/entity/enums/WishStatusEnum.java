package ru.kpfu.itis.entity.enums;

public enum WishStatusEnum {

    SYSTEM(1),
    AUTO(2),
    STUDENT(3),
//    URGENT(4),
    REQUIRED(4),
    TEACHER(5);

    private Integer id;

    WishStatusEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static WishStatusEnum valueOf(Integer id) {
        for (WishStatusEnum wishStatusEnum : WishStatusEnum.values()){
            if(wishStatusEnum.getId().equals(id)) {
                return wishStatusEnum;
            }
        }
        return null;
    }
}