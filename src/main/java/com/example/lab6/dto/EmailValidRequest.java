package com.example.lab6.dto;
import jakarta.validation.constraints.NotNull;

public class EmailValidRequest {
  @NotNull
  private String email;
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
}
