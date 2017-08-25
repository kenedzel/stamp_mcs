package com.stamp.mcs.controller;

import com.stamp.mcs.entities.Biller;
import com.stamp.mcs.services.BillerService;
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
@RequestMapping(value = "/api/v1/biller")
public class BillerController {

    private Logger LOGGER = LoggerFactory.getLogger(BillerController.class);

    @Autowired
    BillerService billerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ListResponse> getBillers() {

        ListResponse<Biller> response = new ListResponse<>();
        ResponseEntity<ListResponse> responseEntity;

        try {
            if (billerService.findAll().isEmpty()) {
                response.setStatusCode(APIStatus.NO_CONTENT);
                response.setStatusMessage(APIStatus.NO_CONTENT, "No billers found");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            } else {
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, "Billers found");
                response.setContent(billerService.findAll());
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (NullPointerException e) {
            response.setStatusCode(APIStatus.NO_CONTENT);
            response.setStatusMessage(APIStatus.NO_CONTENT, "No billers found");
            responseEntity = new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ObjectResponse> saveBillers(@RequestBody Biller biller) {

        ObjectResponse<Biller> response = new ObjectResponse<>();
        ResponseEntity<ObjectResponse> responseEntity;

        try {
            if (billerService.exists(biller.getAccountNumber())) {
                response.setStatusCode(APIStatus.CONFLICT);
                response.setStatusMessage(APIStatus.CONFLICT, "Biller already exists");
                responseEntity = new ResponseEntity<>(response, HttpStatus.CONFLICT);
            } else {
                response.setStatusCode(APIStatus.CREATED);
                response.setStatusMessage(APIStatus.CREATED, "Biller successfully saved");
                response.setContent(billerService.add(biller));
                responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            response.setStatusCode(APIStatus.INTERNAL_SERVER_ERROR);
            response.setStatusMessage(APIStatus.INTERNAL_SERVER_ERROR, "");
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

        @RequestMapping(value = "/{billerAccountNumber}", method = RequestMethod.GET)
        public ResponseEntity<ObjectResponse> getBiller(@PathVariable("billerAccountNumber") String billerAccountNumber) {
            ObjectResponse<Biller> response = new ObjectResponse<>();
            ResponseEntity<ObjectResponse> responseEntity;

        try {
            if (!billerService.exists(billerService.findByAccountNumber(billerAccountNumber).getId())) {
                response.setStatusCode(APIStatus.NOT_FOUND);
                response.setStatusMessage(APIStatus.NOT_FOUND, "Biller not found");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else {
                response.setStatusCode(APIStatus.SUCCESS);
                response.setStatusMessage(APIStatus.SUCCESS, "Biller found");
                response.setContent(billerService.findByAccountNumber(billerAccountNumber));
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