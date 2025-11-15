public class Book {

  public String pigLatin(String word) { return toPig(word); }

  
  public int countWords(String s) {
    int n = 0; boolean in = false;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isLetter(c)) { if (!in) { n++; in = true; } }
      else in = false;
    }
    return n;
  }

  public String translateSentence(String s) {
    if (s == null || s.isEmpty()) return s;
    StringBuilder out = new StringBuilder(), currentWord = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isLetter(c)) currentWord.append(c);
      else { if (currentWord.length() > 0) { out.append(toPig(currentWord.toString())); currentWord.setLength(0); } out.append(c); }
    }
    if (currentWord.length() > 0) out.append(toPig(currentWord.toString()));
    return out.toString();
  }

  private String toPig(String w) {
    if (w.isEmpty()) return w;
    String punc = trailingPunct(w);
    String core = w.substring(0, w.length() - punc.length());
    boolean cap = Character.isUpperCase(core.charAt(0));
    String lower = core.toLowerCase();
    String out;
    if (isVowel(lower.charAt(0))) out = lower + "yay";
    else if (lower.length() == 1) out = lower + "ay";
    else { int i = 0; while (i < lower.length() && !isVowel(lower.charAt(i))) i++; out = lower.substring(i) + lower.substring(0, i) + "ay"; }
    if (cap) out = Character.toUpperCase(out.charAt(0)) + out.substring(1);
    return out + punc;
  }

  private boolean isVowel(char c) { return "aeiou".indexOf(c) >= 0; }
  private String trailingPunct(String x) {
    int i = x.length();
    while (i > 0) { char c = x.charAt(i - 1); if (c=='.'||c==','||c=='!'||c=='?') i--; else break; }
    return x.substring(i);
  }
}
