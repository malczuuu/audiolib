package io.github.malczuuu.audiolib.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlbumConfig {

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
}
