package com.christo.database.service;

/**
 *
 * @author christo
 */
import com.christo.database.entity.EventAudit;
import com.christo.database.dto.EventAuditDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventAuditApiService  {
    
    @Autowired 
    private EventAuditService eventAuditService;
  
    public EventAudit saveEventAudit(EventAuditDTO eventAuditDTO) {
        EventAudit eventAudit = new EventAudit();
        eventAudit.setRequestId(eventAuditDTO.getRequestId());
        eventAudit.setEventName(eventAuditDTO.getEventName());
        eventAudit.setEventBody(eventAuditDTO.getEventBody());
        return eventAuditService.saveEventAudit(eventAudit);
    }
  
    public List<EventAuditDTO> fetchEventAuditList() {
        List<EventAudit> eventAuditList = (List<EventAudit>) eventAuditService.fetchEventAuditList();
        List<EventAuditDTO> eventAuditDTOList = new ArrayList<>();
        EventAuditDTO eventAuditBean;
        for (EventAudit eventAudit : eventAuditList) {
            eventAuditBean = new EventAuditDTO();
            eventAuditBean.setId(eventAudit.getId());
            eventAuditBean.setRequestId(eventAudit.getRequestId());
            eventAuditBean.setEventName(eventAudit.getEventName());
            eventAuditBean.setEventBody(eventAudit.getEventBody());
            eventAuditBean.setDateCreated(eventAudit.getDateCreated());
            eventAuditDTOList.add(eventAuditBean);
        }
        return eventAuditDTOList;
    }

    public EventAuditDTO fetchEventAuditById(Long eventAuditId) {
        EventAudit eventAudit = (EventAudit) eventAuditService.fetchEventAuditById(eventAuditId);
        EventAuditDTO eventAuditBean = new EventAuditDTO();
        eventAuditBean.setId(eventAudit.getId());
        eventAuditBean.setRequestId(eventAudit.getRequestId());
        eventAuditBean.setEventName(eventAudit.getEventName());
        eventAuditBean.setEventBody(eventAudit.getEventBody());
            eventAuditBean.setDateCreated(eventAudit.getDateCreated());
        return eventAuditBean;
    }

    public EventAuditDTO fetchEventAuditByRequestId(String requestId) {
        EventAudit eventAudit = (EventAudit) eventAuditService.fetchEventAuditByRequestId(requestId);
        EventAuditDTO eventAuditBean = new EventAuditDTO();
        eventAuditBean.setId(eventAudit.getId());
        eventAuditBean.setRequestId(eventAudit.getRequestId());
        eventAuditBean.setEventName(eventAudit.getEventName());
        eventAuditBean.setEventBody(eventAudit.getEventBody());
            eventAuditBean.setDateCreated(eventAudit.getDateCreated());
        return eventAuditBean;
    }

    public List<EventAuditDTO> fetchEventAuditByEventName(String eventName) {
        List<EventAudit> eventAuditList = eventAuditService.fetchEventAuditByEventName(eventName);
        List<EventAuditDTO> eventAuditDTOList = new ArrayList<>();
        EventAuditDTO eventAuditBean;
        for (EventAudit eventAudit : eventAuditList) {
            eventAuditBean = new EventAuditDTO();
            eventAuditBean.setId(eventAudit.getId());
            eventAuditBean.setRequestId(eventAudit.getRequestId());
            eventAuditBean.setEventName(eventAudit.getEventName());
            eventAuditBean.setEventBody(eventAudit.getEventBody());
            eventAuditBean.setDateCreated(eventAudit.getDateCreated());
            eventAuditDTOList.add(eventAuditBean);
        }
        return eventAuditDTOList;
    }
  
}