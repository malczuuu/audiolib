package io.github.malczuuu.audiolib.common;

public class TrackNumberFormatter {

  public String format(int total, int track) {
    int level = Integer.toString(total).length();
    StringBuilder numAsString = new StringBuilder("" + track);
    while (level > numAsString.length()) {
      numAsString.insert(0, "0");
    }
    return numAsString.toString();
  }
}
