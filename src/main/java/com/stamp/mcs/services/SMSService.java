package com.stamp.mcs.services;

import com.stamp.mcs.constants.TwilioConstants;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by kenneth on 8/17/17.
 */
@Service
public class SMSService {

    private final Logger LOGGER = LoggerFactory.getLogger(SMSService.class);
    private final String from = "+14156349569";
    private final String to = "+639274302949";

    public void sendSMS(String subscriberAccountNumber, String billerName, double amount) {
        String messageBody = subscriberAccountNumber + " Your payment for "+ billerName + " amounting "+ amount + " has been settled.";
        Twilio.init(TwilioConstants.twilioSid, TwilioConstants.twilioAuthToken);

        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(from),
                messageBody).create();

        LOGGER.info(message.getSid());
    }
}
