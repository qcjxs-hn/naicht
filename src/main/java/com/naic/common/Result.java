package com.naic.common;


import com.naic.ineterfacejk.result;

public class Result<T> implements result {
    private String code;
    private String msg;
    public T data;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code=code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg=msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data=data;
    }


    public Result(){


    }
    public Result(T data){
        this.data=data;
    }
    public static Result success() {
        Result result=new Result<>();
        result.setCode("200");
        result.setMsg("成功");
        return result;
    }
    public static <T> Result<T> success(T data) {
        Result<T> result=new Result<>(data);
        result.setCode("200");
        result.setMsg("成功");
        return result;
    }

    public static Result<?> error(String code, String msg) {
        Result result=new Result<>();
        result.setCode(code);
        result.setMsg(msg);

        return result;
    }



}
