package io.github.malczuuu.audiolib.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.github.malczuuu.audiolib.core.adapter.SimpleAdapter;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SingleMediaFileHandlerTests {

  private SingleMediaFileHandler handler;

  @BeforeEach
  void beforeEach() {
    AlbumConfig albumConfig =
        new AlbumConfig("Lord of the Rings", "The Two Towers", "J. R. R. Tolkien", "John Doe", "");
    SingleMediaFileConfig processingConfig =
        new SingleMediaFileConfig(albumConfig, 23, "/output/lotr2", 34);
    handler = new SingleMediaFileHandler(processingConfig);
  }

  @Test
  void shouldHaveCorrectAlbumName() throws IOException {
    MediaFile mediaFile = new MediaFile(new SimpleAdapter());

    handler.process(mediaFile);

    assertEquals("Lord of the Rings. The Two Towers", mediaFile.getAlbum());
  }

  @Test
  void shouldHaveCorrectTrackTitle() throws IOException {
    MediaFile mediaFile = new MediaFile(new SimpleAdapter());

    handler.process(mediaFile);

    assertEquals("The Two Towers #23", mediaFile.getTitle());
  }

  @Test
  void shouldHaveCorrectTrackArtist() throws IOException {
    MediaFile mediaFile = new MediaFile(new SimpleAdapter());

    handler.process(mediaFile);

    assertEquals("J. R. R. Tolkien", mediaFile.getArtist());
  }

  @Test
  void shouldHaveCorrectAlbumArtist() throws IOException {
    MediaFile mediaFile = new MediaFile(new SimpleAdapter());

    handler.process(mediaFile);

    assertEquals("John Doe", mediaFile.getAlbumArtist());
  }

  @Test
  void shouldHaveCorrectTrackNumber() throws IOException {
    MediaFile mediaFile = new MediaFile(new SimpleAdapter());

    handler.process(mediaFile);

    assertEquals("23", mediaFile.getTrack());
  }

  @Test
  void shouldHaveDiacriticsRemoved() throws IOException {
    MediaFile mediaFile = new MediaFile(new SimpleAdapter());

    AlbumConfig albumConfig =
        new AlbumConfig("Wiedźmin", "Wieża Jaskółki", "Andrzej Sapkowski", "SuperNOVA", "");
    SingleMediaFileConfig processingConfig =
        new SingleMediaFileConfig(albumConfig, 4, "/output/witcher4", 34);
    handler = new SingleMediaFileHandler(processingConfig);

    handler.process(mediaFile);

    assertEquals("Wiedzmin. Wieza Jaskolki", mediaFile.getAlbum());
    assertEquals("Wieza Jaskolki #04", mediaFile.getTitle());
  }
}
