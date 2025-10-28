package com.example.lab6.dto;
import jakarta.validation.constraints.NotNull;

public class PasswordQualityRequest {
  @NotNull
  private String password;
  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }
}
