package com.stamp.mcs.utils.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stamp.mcs.utils.APIStatus;

/**
 * Created by kenneth on 7/19/17.
 */
public class DefaultResponse {

    @JsonProperty("status_code")
    private int statusCode;

    @JsonProperty("status_message")
    private String statusMessage;

    public DefaultResponse() {
    }

    public DefaultResponse(APIStatus apiStatus) {
        setStatusCode(apiStatus);
    }

    public DefaultResponse(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    @Deprecated
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusCode(APIStatus commonStatusCode) {
        this.statusCode = commonStatusCode.getCode();
    }

    @Deprecated
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setStatusMessage(APIStatus commonStatusMessage) {
        this.statusMessage = commonStatusMessage.getMessage();
    }

    public void setStatusMessage(APIStatus commonStatusMessage, String additionalMessage) {
        this.statusMessage = String.format(
                "%s - %s",
                commonStatusMessage.getMessage(),
                additionalMessage
        );
    }
}
