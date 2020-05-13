package com.example.quartzservice.enums;

public enum JobStatusEnum {

    FROZEN(1, "frozen"),

    UNFROZEN(2, "unfrozen");


    private int code;

    private String desc;

    JobStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
