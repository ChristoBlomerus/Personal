package com.christo.restful.api.response;

import com.christo.database.dto.EventAuditDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 *
 * @author christo
 */
public class EventAuditResponse extends APIResponse{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private EventAuditDTO eventAudit;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<EventAuditDTO> eventAudits;

    public EventAuditResponse(String responseCode, String responseMessage) {
        super(responseCode, responseMessage);
    }

    public EventAuditDTO getEventAudit() {
        return eventAudit;
    }

    public void setEventAudit(EventAuditDTO eventAudit) {
        this.eventAudit = eventAudit;
    }

    public List<EventAuditDTO> getEventAudits() {
        return eventAudits;
    }

    public void setEventAudits(List<EventAuditDTO> eventAudits) {
        this.eventAudits = eventAudits;
    }
    
}
