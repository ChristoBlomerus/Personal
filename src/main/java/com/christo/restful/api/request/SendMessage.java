package com.christo.restful.api.request;

/**
 *
 * @author christo
 */
public class SendMessage extends APIRequest{
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
