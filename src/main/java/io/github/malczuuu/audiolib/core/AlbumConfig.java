package io.github.malczuuu.audiolib.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class AlbumConfig {

  public static final AlbumConfig DEFAULT = new AlbumConfig("", "", "", "", "");

  public static AlbumConfigCreator withObjectMapper(ObjectMapper objectMapper) {
    return new AlbumConfigCreator().withObjectMapper(objectMapper);
  }

  private String bookSeries;
  private String bookTitle;
  private String artist;
  private String albumArtist;
  private String description;

  @JsonCreator
  public AlbumConfig(
      @JsonProperty("book_series") String bookSeries,
      @JsonProperty("book_title") String bookTitle,
      @JsonProperty("artist") String artist,
      @JsonProperty("album_artist") String albumArtist,
      @JsonProperty("description") String description) {
    this.bookSeries = bookSeries;
    this.bookTitle = bookTitle;
    this.artist = artist;
    this.albumArtist = albumArtist;
    this.description = description;
  }

  @JsonProperty("book_series")
  public String getBookSeries() {
    return bookSeries;
  }

  @JsonProperty("book_title")
  public String getBookTitle() {
    return bookTitle;
  }

  @JsonProperty("artist")
  public String getArtist() {
    return artist;
  }

  @JsonProperty("album_artist")
  public String getAlbumArtist() {
    return albumArtist;
  }

  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  // TODO Builder

  public static class AlbumConfigCreator {

    private final ObjectMapper objectMapper;
    private final FileSystem fileSystem;

    private AlbumConfigCreator() {
      this(null, null);
    }

    private AlbumConfigCreator(ObjectMapper objectMapper, FileSystem fileSystem) {
      this.objectMapper = objectMapper;
      this.fileSystem = fileSystem;
    }

    public AlbumConfigCreator withObjectMapper(ObjectMapper objectMapper) {
      return new AlbumConfigCreator(objectMapper, fileSystem);
    }

    public AlbumConfigCreator withFileSystem(FileSystem fileSystem) {
      return new AlbumConfigCreator(objectMapper, fileSystem);
    }

    public AlbumConfig loadFromFile(String filename) throws IOException {
      if (fileSystem == null) {
        throw new IOException("Unable to load config file due to not initialized FileSystem");
      }
      String jsonString = fileSystem.readAllLines(filename);
      return loadFromJsonString(jsonString);
    }

    public AlbumConfig loadFromJsonString(String jsonString) throws IOException {
      if (objectMapper == null) {
        throw new IOException("Unable to parse JSON string due to not initialized ObjectMapper");
      }
      return objectMapper.readValue(jsonString, AlbumConfig.class);
    }
  }
}
