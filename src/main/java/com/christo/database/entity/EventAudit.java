package com.christo.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author christo
 */
@Entity
@Table(name = "EVENT_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class EventAudit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    @Column(name = "REQUEST_ID")
    private String requestId;
    @Column(name = "EVENT_BODY")
    private String eventBody;
    @Column(name = "EVENT_NAME")
    private String eventName;
    @CreatedDate
    @Column(name = "DATE_CREATED")
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

    @Override
    public String toString() {
        return "EventAudit{" + "id=" + id + ", requestId=" + requestId + ", eventBody=" + eventBody + ", eventName=" + eventName + ", dateCreated=" + dateCreated + '}';
    }
    
}
