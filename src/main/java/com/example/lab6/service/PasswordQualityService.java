package com.example.lab6.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PasswordQualityService {
  private static final Set<String> BAD_LIST = Set.of("password","123456","qwerty","letmein","admin","welcome");

  public static class Result {
    public final int score; public final String strength; public final List<String> reasons;
    public Result(int s, String str, List<String> r){ score=s; strength=str; reasons=r; }
  }

  public Result score(String pwd) {
    List<String> reasons = new ArrayList<>();
    if (pwd == null || pwd.isBlank()) { reasons.add("blank password"); return new Result(0,"Weak",reasons); }

    int score = 0, len = pwd.length();
    if (len >= 12) { score += 20; reasons.add("length ≥ 12"); }
    else if (len >= 8) { score += 10; reasons.add("length ≥ 8"); }
    else { reasons.add("length < 8"); }

    boolean lower = pwd.chars().anyMatch(Character::isLowerCase);
    boolean upper = pwd.chars().anyMatch(Character::isUpperCase);
    boolean digit = pwd.chars().anyMatch(Character::isDigit);
    boolean special = pwd.chars().anyMatch(c -> !Character.isLetterOrDigit(c));

    if (lower) score += 15; else reasons.add("no lowercase");
    if (upper) score += 15; else reasons.add("no uppercase");
    if (digit) score += 15; else reasons.add("no digit");
    if (special) score += 15; else reasons.add("no special char");

    if (BAD_LIST.contains(pwd.toLowerCase())) { reasons.add("common password"); score = Math.min(score, 20); }
    if (hasLongRepeat(pwd)) { reasons.add("repeated characters"); score -= 10; }
    if (hasSimpleSequence(pwd)) { reasons.add("simple sequence"); score -= 10; }
    if (pwd.contains(" ")) { reasons.add("contains whitespace"); score -= 5; }

    score = Math.max(0, Math.min(100, score));
    String strength = (score>=90)?"Excellent":(score>=70)?"Strong":(score>=40)?"Medium":"Weak";
    return new Result(score,strength,reasons);
  }

  private boolean hasLongRepeat(String s) {
    int run = 1;
    for (int i=1;i<s.length();i++){ run = (s.charAt(i)==s.charAt(i-1))?run+1:1; if (run>=3) return true; }
    return false;
  }
  private boolean hasSimpleSequence(String s) {
    if (s.length() < 3) return false;
    for (int i=2;i<s.length();i++){
      int a=s.charAt(i-2), b=s.charAt(i-1), c=s.charAt(i);
      if ((b==a+1 && c==b+1) || (b==a-1 && c==b-1)) return true;
    }
    return false;
  }
}
