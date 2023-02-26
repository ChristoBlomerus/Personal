package com.christo.database.service;

/**
 *
 * @author christo
 */
import com.christo.database.entity.EventAudit;
import com.christo.database.repository.EventAuditRepository;
import com.christo.database.dto.EventAuditDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventAuditServiceImpl implements EventAuditService {
    
    @Autowired
    private EventAuditRepository eventAuditRepository;
  
    @Override
    public EventAudit saveEventAudit(EventAudit department) {
        return eventAuditRepository.save(department);
    }
  
    @Override 
    public List<EventAudit> fetchEventAuditList() {
        EventAuditDTO eventAuditBean = new EventAuditDTO();
        return (List<EventAudit>) eventAuditRepository.findAll();
    }

    @Override
    public EventAudit fetchEventAuditById(Long eventAuditId) {
        return (EventAudit) eventAuditRepository.findById(eventAuditId).get();
    }

    @Override
    public EventAudit fetchEventAuditByRequestId(String requestId) {
        return (EventAudit) eventAuditRepository.findByRequestId(requestId).get();
    }

    @Override
    public List<EventAudit> fetchEventAuditByEventName(String eventName) {
        return (List<EventAudit>) eventAuditRepository.findAllByEventName(eventName);
    }
  
}