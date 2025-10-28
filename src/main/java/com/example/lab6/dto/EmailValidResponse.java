package com.example.lab6.dto;
import java.util.List;

public class EmailValidResponse {
  private boolean valid;
  private List<String> reasons;

  public EmailValidResponse(boolean valid, List<String> reasons) {
    this.valid = valid; this.reasons = reasons;
  }
  public boolean isValid() { return valid; }
  public List<String> getReasons() { return reasons; }
}
