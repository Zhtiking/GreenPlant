package com.greeplant.GreenPlant.util;

/**
 * 返回工具类
 */

public class JsonData {

    private int code;

    private Object data;

    private String msg;

    //成功失败处理
    public static JsonData BuildSuccess(int code,String msg){
        return new JsonData(0,msg);
    }
    public static JsonData BuildSuccess(Object data){
        return new JsonData(0,data);
    }

    public static JsonData BuildError(String msg){
        return new JsonData(-1,"",msg);
    }

    public static JsonData BuildError(int code,String msg){
        return new JsonData(code,"",msg);
    }


    //构造函数

    public JsonData(){}

    public JsonData(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public JsonData(Object data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public JsonData(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


    //Set、Get方法

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    //toString

    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
