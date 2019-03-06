package com.bs.entity;

import java.io.Serializable;

/**
 * Created by admin on 2019/3/4.
 */

public class ServerResult implements Serializable{

    private int state;
    private String message;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
