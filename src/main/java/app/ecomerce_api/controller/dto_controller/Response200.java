package app.ecomerce_api.controller.dto_controller;

import com.fasterxml.jackson.annotation.JsonView;

import app.ecomerce_api.Config.View;

public class Response200 {

    private String message;
    private int code;

    @JsonView(View.Public.class)
    private Object data;

    public Response200() {
    }

    public Response200(String message, int code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public Response200 setResponse200(String message, int code, Object data) {
        this.message = message;
        this.code = code;
        this.data = data;
        return this;
    }
    
}
