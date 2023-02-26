package com.christo.database.dto;

import com.fasterxml.jackson.annotation.JsonRawValue;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author christo
 */
@Component
public class EventAuditDTO {
    private long id;
    private String requestId;
    @JsonRawValue
    private String eventBody;
    private String eventName;
    private Date dateCreated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getEventBody() {
        return eventBody;
    }

    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "EventAudit{" + "id=" + id + ", requestId=" + requestId + ", eventBody=" + eventBody + ", eventName=" + eventName + ", dateCreated=" + dateCreated + '}';
    }
}
