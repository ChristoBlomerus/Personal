package com.christo.database.repository;

import com.christo.database.entity.EventAudit;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author christo
 */
@Repository
public interface EventAuditRepository extends JpaRepository<EventAudit, Long> {
    
    Optional<EventAudit> findByRequestId(String requestId);
    
    List<EventAudit> findAllByEventName(String eventName);
    
}
