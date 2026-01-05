package com.example.gameRecommender.model;

public class Result {
    private Integer code; //1是成功，0是失败；
    private String msg; //成功消息或者失败消息；
    private Object data; //放数据；

//    public static Result success(List<QSBeanOut> qsBeanOut) {
//        return new Result(1, "success",qsBeanOut);
//    }

//    public static Result success(QSBeanPage qsBeanPage) {
//        return new Result(1, "success",qsBeanPage);
//    }

    //    public static Result success(List<QSBeanOutManage> qsBeanOutManage) {
//        return new Result(1, "success",qsBeanOutManage);
//    }
    public static Result success(Object data) {
        return new Result(1, "success",data);
    }
    //...get,set,toString
    //构造方法；
    //静态success方法和error方法；

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "Result{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }

    public  Result()
    {

    }

    public Result(Integer code, String msg, Object data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(String msg)
    {
        return new Result(1, msg,null);
    }



    public static Result error(String msg)
    {
        return new Result(0,msg,null);
    }
}
