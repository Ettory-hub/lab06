package com.example.lab6.service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EmailValidationService {
  private static final Pattern BASIC = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,24}$");

  public static class Result { public final boolean valid; public final List<String> reasons;
    public Result(boolean v, List<String> r){ valid=v; reasons=r; } }

  public Result validate(String email){
    List<String> reasons = new ArrayList<>();
    if (email == null || email.isBlank()) { reasons.add("blank email"); return new Result(false,reasons); }
    boolean basic = BASIC.matcher(email).matches();
    if (!basic) reasons.add("fails basic pattern");

    String lower = email.toLowerCase();
    if (lower.contains("..")) reasons.add("consecutive dots");
    int at = lower.indexOf('@');
    if (at <= 0 || at == lower.length()-1) reasons.add("missing/invalid @");
    if (at > 0) {
      String domain = lower.substring(at+1);
      if (domain.startsWith("-") || domain.endsWith("-")) reasons.add("domain starts/ends with hyphen");
      if (domain.startsWith(".") || domain.endsWith(".")) reasons.add("domain starts/ends with dot");
      for (String label : domain.split("\\.")) {
        if (label.isEmpty()) { reasons.add("empty domain label"); break; }
        if (label.startsWith("-") || label.endsWith("-")) { reasons.add("label starts/ends with hyphen"); break; }
      }
    }
    return new Result(reasons.isEmpty(), reasons);
  }
}
