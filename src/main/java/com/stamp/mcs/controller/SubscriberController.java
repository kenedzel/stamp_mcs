package com.stamp.mcs.controller;

import com.stamp.mcs.entities.Bill;
import com.stamp.mcs.entities.Subscriber;
import com.stamp.mcs.services.BillService;
import com.stamp.mcs.services.SubscriberService;
import com.stamp.mcs.utils.APIStatus;
import com.stamp.mcs.utils.responses.DefaultResponse;
import com.stamp.mcs.utils.responses.generic.ListResponse;
import com.stamp.mcs.utils.responses.generic.ObjectResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kenneth on 8/14/17.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/subscriber")
public class SubscriberController {

    private Logger LOGGER = LoggerFactory.getLogger(SubscriberController.class);

    @Autowired
    SubscriberService subscriberService;

    @Autowired
    BillService billService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ListResponse> getSubscribers() {

        ListResponse<Subscriber> response = new ListResponse<>();
        ResponseEntity<ListResponse> responseEntity;

        try {
            if (subscriberService.findAll().isEmpty()) {
                response.setStatusCode(APIStatus.NO_CONTENT);
                response.setStatusMessage(APIStatus.NO_CONTENT, "No subscribers found");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, "Subscribers found");
                response.setContent(subscriberService.findAll());
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ObjectResponse> saveSubscriber(@RequestBody Subscriber subscriber) {
        ObjectResponse<Subscriber> response = new ObjectResponse<>();
        ResponseEntity<ObjectResponse> responseEntity;

        try {
            //not going to work
            if (subscriberService.exists(subscriber.getAccountNumber())) {
                response.setStatusCode(APIStatus.CONFLICT);
                response.setStatusMessage(APIStatus.CONFLICT, "Subscriber already exists");
                responseEntity = new ResponseEntity<>(response, HttpStatus.CONFLICT);
            } else {
                newBillAccount(subscriber.getAccountNumber(), subscriber.getMsisdn());
                response.setStatusCode(APIStatus.CREATED);
                response.setStatusMessage(APIStatus.CREATED, "Subsciber successfully saved");
                response.setContent(subscriberService.add(subscriber));
                responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    private void newBillAccount(String subscriberAccountNumber, String msisdn) {
        Bill bill = new Bill();
        bill.setBillerAccountNumber("123456");
        bill.setSubscriberAccountNumber(subscriberAccountNumber);
        bill.setOutstandingBalance(0);
        bill.setPaid(true);
        bill.setSubscriberMsisdn(msisdn);
        billService.add(bill);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ObjectResponse> getSubscriber(@PathVariable("id") String id) {
        ObjectResponse<Subscriber> response = new ObjectResponse<>();
        ResponseEntity<ObjectResponse> responseEntity;

        try {
            if (!subscriberService.exists(subscriberService.findOne(id).getId())) {
                response.setStatusCode(APIStatus.NOT_FOUND);
                response.setStatusMessage(APIStatus.NOT_FOUND, "Subscriber not found");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, "Subscriber found");
                response.setContent(subscriberService.findOne(id));
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
