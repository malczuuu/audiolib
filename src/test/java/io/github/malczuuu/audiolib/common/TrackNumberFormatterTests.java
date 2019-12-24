package io.github.malczuuu.audiolib.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TrackNumberFormatterTests {

  private TrackNumberFormatter formatter;

  @BeforeEach
  void beforeEach() {
    formatter = new TrackNumberFormatter();
  }

  @ParameterizedTest
  @CsvSource({"5,50,05", "65,352,065", "6,102,006", "1,10,01", "1,6,1"})
  void shouldFormatTwoDigitsTrack(int track, int total, String expectedFormattedTrack) {

    String formattedTrack = formatter.format(total, track);

    assertEquals(expectedFormattedTrack, formattedTrack);
  }
}
