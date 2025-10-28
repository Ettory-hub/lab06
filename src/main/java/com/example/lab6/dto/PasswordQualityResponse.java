package com.example.lab6.dto;
import java.util.List;

public class PasswordQualityResponse {
  private int score;
  private String strength;
  private List<String> reasons;

  public PasswordQualityResponse(int score, String strength, List<String> reasons) {
    this.score = score; this.strength = strength; this.reasons = reasons;
  }
  public int getScore() { return score; }
  public String getStrength() { return strength; }
  public List<String> getReasons() { return reasons; }
}