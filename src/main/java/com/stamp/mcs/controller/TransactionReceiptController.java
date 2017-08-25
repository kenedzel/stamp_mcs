package com.stamp.mcs.controller;

import com.stamp.mcs.entities.Biller;
import com.stamp.mcs.entities.Subscriber;
import com.stamp.mcs.entities.TransactionReceipt;
import com.stamp.mcs.services.BillerService;
import com.stamp.mcs.services.SubscriberService;
import com.stamp.mcs.services.TransactionReceiptService;
import com.stamp.mcs.utils.APIStatus;
import com.stamp.mcs.utils.responses.generic.ListResponse;
import com.stamp.mcs.utils.responses.generic.ObjectResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by kenneth on 8/16/17.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/receipt")
public class TransactionReceiptController {

    private Logger LOGGER = LoggerFactory.getLogger(TransactionReceiptController.class);

    @Autowired
    TransactionReceiptService transactionReceiptService;

    @Autowired
    SubscriberService subscriberService;

    @Autowired
    BillerService billerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ListResponse> findAll() {
        ListResponse<TransactionReceipt> response = new ListResponse<>();
        ResponseEntity<ListResponse> responseEntity;

        try {
            if (transactionReceiptService.findAll().isEmpty()) {
                response.setStatusCode(APIStatus.NO_CONTENT);
                response.setStatusMessage(APIStatus.NO_CONTENT, "No receipts found");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, "Receipts found");
                response.setContent(transactionReceiptService.findAll());
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/save/{subscriberAccountNumber}/{billerAccountNumber}", params = {"amount", "isSuccess"}, method = RequestMethod.POST)
    public ResponseEntity<ObjectResponse> save(@PathVariable("subscriberAccountNumber") String subscriberAccountNumber,
                                               @PathVariable("billerAccountNumber") String billerAccountNumber,
                                               @RequestParam("amount") double amount,
                                               @RequestParam("isSuccess") String isSuccess) {
        ObjectResponse<TransactionReceipt> response = new ObjectResponse<>();
        ResponseEntity<ObjectResponse> responseEntity;
        LOGGER.info("Subs Acc num: {}", subscriberAccountNumber);
        LOGGER.info("Biller Acc num: {}", billerAccountNumber);
        LOGGER.info("amount: {}", amount);
        LOGGER.info("isSuccess: {}", Boolean.parseBoolean(isSuccess));
        try {
            response.setContent(transactionReceiptService.add(assembleReceipt(subscriberAccountNumber, billerAccountNumber, amount, isSuccess)));
            response.setStatusCode(APIStatus.CREATED);
            response.setStatusMessage(APIStatus.CREATED, "Transaction Receipt successfully saved");
            responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    private TransactionReceipt assembleReceipt(String subscriberAccountNumber, String billerAccountNumber, double amount, String isSuccess) {
        TransactionReceipt transactionReceipt = new TransactionReceipt();
        transactionReceipt.setDatePaid(new Date().getTime());
        transactionReceipt.setAmount(amount);
        transactionReceipt.setBillerAccountNumber(billerAccountNumber);
        transactionReceipt.setBillerName(getBillerName(billerAccountNumber));
        transactionReceipt.setSubscriberAccountNumber(subscriberAccountNumber);
        transactionReceipt.setSubscriberName(getSubscriberName(subscriberAccountNumber));
        transactionReceipt.setSuccess(Boolean.parseBoolean(isSuccess));
        return transactionReceipt;
    }

    private String getSubscriberName(String subscriberAccountNumber) {
        Subscriber subscriber = subscriberService.findByAccountNumber(subscriberAccountNumber);
        return subscriber.getSubscriberName();
    }

    private String getBillerName(String billerAccountNumber) {
        Biller biller = billerService.findByAccountNumber(billerAccountNumber);
        return biller.getBillerName();
    }
}
