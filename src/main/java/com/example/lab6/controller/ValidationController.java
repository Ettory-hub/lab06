package com.example.lab6.controller;

import com.example.lab6.dto.*;
import com.example.lab6.service.EmailValidationService;
import com.example.lab6.service.PasswordQualityService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*; 

@RestController
@RequestMapping("/api")
@Validated
public class ValidationController {

  private final PasswordQualityService pwService = new PasswordQualityService();
  private final EmailValidationService emailService = new EmailValidationService();

  @PostMapping(path="/password-quality",
               consumes=MediaType.APPLICATION_JSON_VALUE,
               produces=MediaType.APPLICATION_JSON_VALUE)
  public PasswordQualityResponse passwordQuality(@RequestBody PasswordQualityRequest req) {
    var r = pwService.score(req.getPassword());
    return new PasswordQualityResponse(r.score, r.strength, r.reasons);
  }

  @PostMapping(path="/email-address-valid",
               consumes=MediaType.APPLICATION_JSON_VALUE,
               produces=MediaType.APPLICATION_JSON_VALUE)
  public EmailValidResponse emailValid(@RequestBody EmailValidRequest req) {
    var r = emailService.validate(req.getEmail());
    return new EmailValidResponse(r.valid, r.reasons);
  }
}
