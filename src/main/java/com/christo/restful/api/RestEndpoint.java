package com.christo.restful.api;

import com.christo.database.dto.EventAuditDTO;
import com.christo.database.entity.EventAudit;
import com.christo.database.service.EventAuditApiService;
import com.christo.rabbitMQ.RabbitMQService;
import com.christo.restful.api.object.APIQueryParam;
import com.christo.restful.api.request.SendMessage;
import com.christo.restful.api.request.Transaction;
import com.christo.restful.api.response.APIResponse;
import com.christo.restful.api.response.EventAuditResponse;
import java.util.List;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author christo
 */

@RestController
public class RestEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(RestEndpoint.class);
    @Autowired 
    private EventAuditApiService eventAuditApiService;
    @Autowired
    private RabbitMQService rabbitMQInterface;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getProduct() {
       return new ResponseEntity<>("Running", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/sendMessageToRabbitMQ", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody APIResponse sendMessageToRabbitMQ(@RequestHeader("EVENT_NAME") String eventName, @RequestBody SendMessage sendMessage) {
        try {
            sendMessage.setTransactionType("Message");
            if (rabbitMQInterface.sendToQueue(sendMessage, eventName)){
                return new APIResponse("00", "Send message successful");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new APIResponse("99", "Send message failed");
    }
    
    @RequestMapping(value = "/sendTransactionToRabbitMQ", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody APIResponse sendTransactionToRabbitMQ(@RequestHeader("EVENT_NAME") String eventName, @RequestBody Transaction transaction) {
        try {
            if (rabbitMQInterface.sendToQueue(transaction, eventName)){
                return new APIResponse("00", transaction.getTransactionType() + " successful");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new APIResponse("99", transaction.getTransactionType() + " failed");
    }
    
    @RequestMapping(value = "/findEventAuditByRequestId", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody APIResponse findEventAuditByRequestId(@RequestHeader("EVENT_NAME") String eventName, @RequestParam String requestId) {
        try {
            if (rabbitMQInterface.sendToQueue(new APIQueryParam("requestId", requestId), eventName)){
                EventAuditDTO eventAudit = eventAuditApiService.fetchEventAuditByRequestId(requestId);
                EventAuditResponse eventAuditResponse = new EventAuditResponse("00", "Success");
                logger.info("EventAudit found");
                logger.info(eventAudit.toString());
                eventAuditResponse.setEventAudit(eventAudit);
                return eventAuditResponse;
            }
        } catch (NoSuchElementException ex) {
            return new APIResponse("02", "Could not find event audit");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new APIResponse("99", "Something went wrong trying to find event audit");
    }
    
    @RequestMapping(value = "/findEventAuditById", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody APIResponse findEventAuditByRequestId(@RequestHeader("EVENT_NAME") String eventName, @RequestParam long id) {
        try {
            if (rabbitMQInterface.sendToQueue(new APIQueryParam("id", String.valueOf(id)), eventName)){
                EventAuditDTO eventAudit = eventAuditApiService.fetchEventAuditById(id);
                EventAuditResponse eventAuditResponse = new EventAuditResponse("00", "Success");
                logger.info("EventAudit found");
                logger.info(eventAudit.toString());
                eventAuditResponse.setEventAudit(eventAudit);
                return eventAuditResponse;
            }
        } catch (NoSuchElementException ex) {
            return new APIResponse("02", "Could not find event audit");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new APIResponse("99", "Something went wrong trying to find event audit");
    }
    
    @RequestMapping(value = "/findEventAuditsByEventName", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody APIResponse findEventAuditsByEventName(@RequestHeader("EVENT_NAME") String eventName, @RequestParam String searchEventName) {
        try {
            if (rabbitMQInterface.sendToQueue(new APIQueryParam("searchEventName", searchEventName), eventName)){
                List<EventAuditDTO> eventAudits = eventAuditApiService.fetchEventAuditByEventName(searchEventName);
                EventAuditResponse eventAuditResponse = new EventAuditResponse("00", "Success");
                eventAuditResponse.setEventAudits(eventAudits);
                return eventAuditResponse;
            }
        } catch (NoSuchElementException ex) {
            return new APIResponse("02", "Could not find event audit");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new APIResponse("99", "Something went wrong trying to find event audit");
    }
}