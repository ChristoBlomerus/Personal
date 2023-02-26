package com.christo.restful.api.response;

/**
 *
 * @author christo
 */
public class APIResponse {
    private String responseCode;
    private String responseMessage;

    public void APIResponse() {
        
    }

    public APIResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
    
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    
}
