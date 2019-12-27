package io.github.malczuuu.audiolib.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AlbumConfig {

  public static AlbumConfigCreator withObjectMapper(ObjectMapper objectMapper) {
    return new AlbumConfigCreator().withObjectMapper(objectMapper);
  }

  private String bookSeries;
  private String bookTitle;
  private String artist;
  private String albumArtist;
  private String description;
  private String input;
  private String output;

  @JsonCreator
  public AlbumConfig(
      @JsonProperty("book_series") String bookSeries,
      @JsonProperty("book_title") String bookTitle,
      @JsonProperty("artist") String artist,
      @JsonProperty("album_artist") String albumArtist,
      @JsonProperty("description") String description,
      @JsonProperty("input") String input,
      @JsonProperty("output") String output) {
    this.bookSeries = bookSeries;
    this.bookTitle = bookTitle;
    this.artist = artist;
    this.albumArtist = albumArtist;
    this.description = description;
    this.input = input;
    this.output = output;
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

  @JsonProperty("input")
  public String getInput() {
    return input;
  }

  @JsonProperty("output")
  public String getOutput() {
    return output;
  }

  // TODO Builder

  public static class AlbumConfigCreator {

    private ObjectMapper objectMapper;

    private AlbumConfigCreator() {
      this(null);
    }

    private AlbumConfigCreator(ObjectMapper objectMapper) {
      this.objectMapper = objectMapper;
    }

    public AlbumConfigCreator withObjectMapper(ObjectMapper objectMapper) {
      this.objectMapper = objectMapper;
      return this;
    }

    public List<AlbumConfig> loadFromFile(String filename) throws IOException {
      String jsonString = String.join("\n", Files.readAllLines(Paths.get(filename)));
      return loadFromJsonString(jsonString);
    }

    public List<AlbumConfig> loadFromJsonString(String jsonString) throws IOException {
      if (objectMapper == null) {
        objectMapper = new ObjectMapper();
      }
      return Arrays.asList(objectMapper.readValue(jsonString, AlbumConfig[].class));
    }
  }
}
