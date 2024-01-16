package com.OTPVerification.controller;

import com.OTPVerification.service.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OtpController {
    @Autowired
  private TwilioService twilioService;


    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String phoneNumber) {
        twilioService.sendOtp(phoneNumber);
       return ResponseEntity.ok("OTP Sent successfully");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String phoneNumber, @RequestParam String Otp) {


        boolean isverified= twilioService.verifyOtp(phoneNumber,Otp);
        if (isverified) {
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            // Failed verification, redirect to an error page
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
        }
    }
    }


