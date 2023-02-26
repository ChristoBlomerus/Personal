package com.christo.rabbitMQ.object;

/**
 *
 * @author christo
 */
public class EventAuditQueueObj {
    private String eventName;
    private Object body;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

}
