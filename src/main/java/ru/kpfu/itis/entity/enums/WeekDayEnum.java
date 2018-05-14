package ru.kpfu.itis.entity.enums;

public enum WeekDayEnum {

    MONDAY("Понедельник", 1),
    TUESDAY("Вторник", 2),
    WEDNESDAY("Среда", 3),
    THURSDAY("Четверг", 4),
    FRIDAY("Пятница", 5),
    SATURDAY("Суббота", 6),
    SUNDAY("Воскресенье", 7);

    private String name;
    private Integer code;

    WeekDayEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static WeekDayEnum valueOf(Integer code) {
        for(WeekDayEnum weekDayEnum : WeekDayEnum.values()) {
            if(weekDayEnum.getCode().equals(code)) {
                return weekDayEnum;
            }
        }
        return null;
    }
}