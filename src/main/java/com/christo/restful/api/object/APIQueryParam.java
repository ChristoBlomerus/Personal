package com.christo.restful.api.object;

/**
 *
 * @author christo
 */
public class APIQueryParam {
    private String name;
    private String value;

    public APIQueryParam(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
