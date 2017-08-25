package com.stamp.mcs.controller;

import com.stamp.mcs.constants.TwilioConstants;
import com.stamp.mcs.entities.Bill;
import com.stamp.mcs.services.BillService;
import com.stamp.mcs.services.BillerService;
import com.stamp.mcs.services.SMSService;
import com.stamp.mcs.services.SubscriberService;
import com.stamp.mcs.utils.APIStatus;
import com.stamp.mcs.utils.responses.DefaultResponse;
import com.stamp.mcs.utils.responses.generic.ObjectResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kenneth on 8/14/17.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/bill")
public class BillController {

    private Logger LOGGER = LoggerFactory.getLogger(BillController.class);

    @Autowired
    BillService billService;

    @Autowired
    BillerService billerService;

    @Autowired
    SubscriberService subscriberService;

    @Autowired
    SMSService smsService;

    @RequestMapping(value = "/settle/{subscriberAccountNumber}/{billerAccountNumber}", params = {"amount"}, method = RequestMethod.POST)
    public ResponseEntity<DefaultResponse> settleBill(@PathVariable("subscriberAccountNumber") String subscriberAccountNumber,
                                                      @PathVariable("billerAccountNumber") String billerAccountNumber,
                                                      @RequestParam("amount") double amount) {
        DefaultResponse response = new DefaultResponse();
        ResponseEntity<DefaultResponse> responseEntity;

        boolean isBillExists = billService.exists(billService.findBySubscriberAccountNumber(subscriberAccountNumber).getId());
        boolean isBillerExists = billerService.existsOnAccountNumber(billerAccountNumber);
        boolean isSubscriberExists = subscriberService.exists(subscriberService.findByAccountNumber(subscriberAccountNumber).getId());
        try {
            if (!isBillExists || !isBillerExists || !isSubscriberExists) {
                response.setStatusCode(APIStatus.NOT_FOUND);
                response.setStatusMessage(APIStatus.NOT_FOUND, "404 not found");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                billerService.receivePayment(billerAccountNumber, amount);
                billService.settlePayment(subscriberAccountNumber, amount);
                subscriberService.payment(subscriberAccountNumber, amount);
//                LOGGER.info("SID: {}", TwilioConstants.twilioSid);
                smsService.sendSMS(subscriberAccountNumber, billerService.findByAccountNumber(billerAccountNumber).getBillerName(), amount);
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, "Bill has been settled");
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/add/{subscriberAccountNumber}/{billerAccountNumber}", params = {"amount"}, method = RequestMethod.POST)
    public ResponseEntity<ObjectResponse> addBalance(@PathVariable("subscriberAccountNumber") String subscriberAccountNumber,
                                                     @PathVariable("billerAccountNumber") String billerAccountNumber,
                                                     @RequestParam("amount") double amount) {
        ObjectResponse<Bill> response = new ObjectResponse<>();
        ResponseEntity<ObjectResponse> responseEntity;
        Bill bill = billService.findBySubscriberAndBiller(subscriberAccountNumber, billerAccountNumber);
        boolean isBillExists = billService.exists(bill.getId());

        try {
            if (!isBillExists) {
                response.setStatusCode(APIStatus.NOT_FOUND);
                response.setStatusMessage(APIStatus.NOT_FOUND, "Bill not found");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                response.setContent(billService.addBalance(subscriberAccountNumber, billerAccountNumber, amount));
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, "Balance has been added");
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (NullPointerException e) {
            response.setStatusCode(APIStatus.NOT_FOUND);
            response.setStatusMessage(APIStatus.NOT_FOUND, "Bill not found");
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/{subscriberAccountNumber}/{billerAccountNumber}", method = RequestMethod.GET)
    public ResponseEntity<ObjectResponse> getBill(@PathVariable("subscriberAccountNumber") String subscriberAccountNumber,
                                                  @PathVariable("billerAccountNumber") String billerAccountNumber,
                                                  HttpServletRequest httpServletRequest) {
        ObjectResponse<Bill> response = new ObjectResponse<>();
        ResponseEntity<ObjectResponse> responseEntity;

        LOGGER.info("Access Token: {}", httpServletRequest.getHeader("Authorization"));
        try {
        Bill bill = billService.findBySubscriberAndBiller(subscriberAccountNumber, billerAccountNumber);
        LOGGER.info("bill: {}", bill.toString());
        boolean isBillExists = billService.exists(bill.getId());
            if (!billService.exists(bill.getId())) {
                response.setStatusCode(APIStatus.NOT_FOUND);
                response.setStatusMessage(APIStatus.NOT_FOUND, "Bill not found");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                response.setContent(bill);
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, "Bill found");
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (NullPointerException e) {
            response.setStatusCode(APIStatus.NOT_FOUND);
            response.setStatusMessage(APIStatus.NOT_FOUND, "Bill not found");
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}