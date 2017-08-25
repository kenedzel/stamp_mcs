package com.stamp.mcs.controller;

import com.stamp.mcs.entities.Transaction;
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

import java.util.concurrent.TimeoutException;

/**
 * Created by kenneth on 7/24/17.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/transaction")
public class TransactionController {

    Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    TransactionService transactionService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ListResponse> findAll() {
        ListResponse<Transaction> response = new ListResponse<>();
        ResponseEntity<ListResponse> responseEntity;
        try {
            if (transactionService.findAll().isEmpty()) {
                response.setStatusCode(APIStatus.NO_CONTENT);
                response.setStatusMessage(APIStatus.NO_CONTENT, "No Transactions List found");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, "Stamps List found");
                response.setContent(transactionService.findAll());
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
    public ResponseEntity<AddResponse> saveTransaction(@RequestBody Transaction transaction) {
        AddResponse<Transaction> response = new AddResponse<>();
        ResponseEntity<AddResponse> responseEntity;

        try {
            response.setNewItem(transactionService.add(transaction));
            response.setStatusCode(APIStatus.CREATED);
            response.setStatusMessage(APIStatus.CREATED, "Transaction created.");
            responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "Transaction not saved.");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        LOGGER.info("saved", responseEntity);
        return responseEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ObjectResponse> findTransaction(@PathVariable("id") String id) {
        ObjectResponse response = new ObjectResponse();
        ResponseEntity<ObjectResponse> responseEntity;

        try {
            if (transactionService.exists(id)) {
                response.setContent(transactionService.findOne(id));
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, String.format("Transaction %s found",
                        transactionService.findOne(id).getTransactionName()));
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
                LOGGER.info("{}", transactionService.findOne(id));
            } else {
                response.setStatusCode(APIStatus.NOT_FOUND);
                response.setStatusMessage(APIStatus.NOT_FOUND, "No transaction found.");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ObjectResponse> removeTransaction(@PathVariable("id") String id) {
        ObjectResponse response = new ObjectResponse();
        ResponseEntity<ObjectResponse> responseEntity;
        try {
            if (transactionService.exists(id)) {
                transactionService.delete(id);
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, String.format("Transaction has been successfully deleted."));
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
                LOGGER.info("Deleted");
            } else {
                response.setStatusCode(APIStatus.NOT_FOUND);
                response.setStatusMessage(APIStatus.NOT_FOUND, "No Transaction found.");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ObjectResponse> updateTransaction(@PathVariable("id") String id,
                                                            @RequestBody Transaction transaction) {
        ObjectResponse response = new ObjectResponse();
        ResponseEntity<ObjectResponse> responseEntity;
        try {
            if (transactionService.exists(id)) {
                response.setContent(transactionService.update(transaction));
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, String.format("%s has been updated",
                                                                            transactionService.findOne(id).getTransactionName()));
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setStatusCode(APIStatus.NOT_FOUND);
                response.setStatusMessage(APIStatus.NOT_FOUND, "No Transaction found.");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
