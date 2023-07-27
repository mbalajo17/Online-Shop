package com.symbiance.onlinestore.response;

public class Apiresponse {

    private String message;

private boolean success;

    public Apiresponse(String message,boolean success) {
        this.message = message;
        this.success=success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
