package ru.kpfu.itis.entity.enums;

public enum UserRoleEnum {

    STUDENT(1),
    TEACHER(2),
    EXPERT(3);

    private Integer id;

    UserRoleEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static UserRoleEnum valueOf(Integer id) {
        for (UserRoleEnum statusEnum : UserRoleEnum.values()){
            if(statusEnum.getId().equals(id)) {
                return statusEnum;
            }
        }
        return null;
    }
}