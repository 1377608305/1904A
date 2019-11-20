package com.example.lenovo.day01;

/**
 * Created by lenovo on 2019/11/18.
 */

public class Bean {

    @Override
    public String toString() {
        return "Bean{" +
                "code=" + code +
                ", ret='" + ret + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    /**
     * code : 200
     * ret : success
     * data : qhvk
     */

    private int code;
    private String ret;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
