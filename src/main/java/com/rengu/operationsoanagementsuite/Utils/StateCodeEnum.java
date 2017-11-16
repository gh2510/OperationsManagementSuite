package com.rengu.operationsoanagementsuite.Utils;

public enum StateCodeEnum {
    OK(200, "OK"),
    InternalServerError(500, "Internal Server Error");
    private int stateCode;
    private String message;

    StateCodeEnum(int stateCode, String message) {
        this.stateCode = stateCode;
        this.message = message;
    }

    public int getStateCode() {
        return stateCode;
    }

    public String getMessage() {
        return message;
    }
}
