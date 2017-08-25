package com.stamp.mcs.controller;

import com.stamp.mcs.entities.Stamp;
import com.stamp.mcs.entities.Transaction;
import com.stamp.mcs.services.StampService;
import com.stamp.mcs.services.TransactionService;
import com.stamp.mcs.utils.APIStatus;
import com.stamp.mcs.utils.responses.generic.AddResponse;
import com.stamp.mcs.utils.responses.generic.ListResponse;
import com.stamp.mcs.utils.responses.generic.ObjectResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kenneth on 7/19/17.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/stamp")
public class StampController {

    private final Logger LOGGER = LoggerFactory.getLogger(StampController.class);

    @Autowired
    StampService stampService;

    @RequestMapping(value = "/in", method = RequestMethod.GET)
    public ResponseEntity<AddResponse> stampIn() {
        AddResponse<Stamp> response = new AddResponse<>();
        ResponseEntity<AddResponse> responseEntity;
        response.setStatusCode(APIStatus.CREATED);
        response.setStatusMessage(APIStatus.CREATED, "Your stamp has been created.");
        response.setNewItem(stampService.add(new Stamp()));
        responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
        LOGGER.info(responseEntity.getBody() + "\n");
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ListResponse> getAll() {
        ListResponse<Stamp> response = new ListResponse<>();
        ResponseEntity<ListResponse> responseEntity;
        try {
            if (stampService.findAll().isEmpty()) {
                response.setStatusCode(APIStatus.NO_CONTENT);
                response.setStatusMessage(APIStatus.NO_CONTENT, "No Stamps List found");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, "Stamps List found");
                response.setContent(stampService.findAll());
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.info(responseEntity.getBody() + "\n");
        return responseEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ObjectResponse> getStamp(@PathVariable("id") String id) {
        ObjectResponse<Stamp> response = new ObjectResponse<>();
        ResponseEntity<ObjectResponse> responseEntity;
        try {
            if (stampService.findOne(id) == null) {
                response.setStatusCode(APIStatus.NOT_FOUND);
                response.setStatusMessage(APIStatus.NOT_FOUND, "No Stamp found");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, "Stamp found");
                response.setContent(stampService.findOne(id));
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, ".");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info(responseEntity.getStatusCodeValue() + "\n");
        return responseEntity;
    }

    @RequestMapping(value = "/transact", method = RequestMethod.GET)
    public ResponseEntity<Transaction> generateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAccountNumber("1111123");
        transaction.setReferenceNumber("1282BSD22");
        transaction.setTransactionName("sample transact");

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }
}
