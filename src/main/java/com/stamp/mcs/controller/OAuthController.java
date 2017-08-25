package com.stamp.mcs.controller;

import com.stamp.mcs.entities.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by kenneth on 8/15/17.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/oauth")
public class OAuthController {

    @RequestMapping(value = "/accessToken", params = {"email"}, method = RequestMethod.POST)
    public ResponseEntity<Token> getToken(@RequestParam("email") String email) {
        return new ResponseEntity<>(getAccessToken(email), HttpStatus.OK);
    }

    public Token getAccessToken(String email) {
        Token token = new Token();
        token.setDeveloperEmail(email);
        token.setIssuedAt(new Date().getTime());

        if (email.equals("rejected@email.com")) {
            token.setStatus("rejected");
        } else {
            token.setStatus("approved");
        }

        token.setExpiresIn(3600);
        token.setTokenType("Bearer");
        token.setAccessToken("abc123ABCoaWeeEepqoaQeaa112");

        return token;
    }
}
