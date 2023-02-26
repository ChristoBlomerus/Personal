package com.christo.database.service;

import com.christo.database.entity.EventAudit;
import java.util.List;

/**
 *
 * @author christo
 */
public interface EventAuditService {

    EventAudit saveEventAudit(EventAudit eventAudit);
  
    List<EventAudit> fetchEventAuditList();
  
    EventAudit fetchEventAuditById(Long eventAuditId);
  
    EventAudit fetchEventAuditByRequestId(String eventAuditId);
    
    List<EventAudit> fetchEventAuditByEventName(String eventName);
    
}
