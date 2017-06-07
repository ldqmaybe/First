package com.first.base;

/**
 * @author Admin
 * @time 2016/11/24 0024.14:16
 */
public class BaseHttpResult<T> {
    private T data;
    private String result;
    private String remark;
    private String error_code;
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getError_code() {
        return error_code;
    }
    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
