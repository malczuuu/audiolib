package io.github.malczuuu.audiolib.core;

public class SingleMediaFileConfig {

  private final String bookSeries;
  private final String bookTitle;
  private final String artist;
  private final String albumArtist;
  private final String description;

  private final int track;

  private final String outputDirectory;
  private final int tracksTotal;

  public SingleMediaFileConfig(
      AlbumConfig config, int track, String outputDirectory, int tracksTotal) {
    this(
        config.getBookSeries(),
        config.getBookTitle(),
        config.getArtist(),
        config.getAlbumArtist(),
        config.getDescription(),
        track,
        outputDirectory,
        tracksTotal);
  }

  private SingleMediaFileConfig(
      String bookSeries,
      String bookTitle,
      String artist,
      String albumArtist,
      String description,
      int track,
      String outputDirectory,
      int tracksTotal) {
    this.bookSeries = bookSeries;
    this.bookTitle = bookTitle;
    this.artist = artist;
    this.albumArtist = albumArtist;
    this.description = description;
    this.track = track;
    this.outputDirectory = outputDirectory;
    this.tracksTotal = tracksTotal;
  }

  public String getBookSeries() {
    return bookSeries;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public String getArtist() {
    return artist;
  }

  public String getAlbumArtist() {
    return albumArtist;
  }

  public String getDescription() {
    return description;
  }

  public int getTrack() {
    return track;
  }

  public String getOutputDirectory() {
    return outputDirectory;
  }

  public int getTracksTotal() {
    return tracksTotal;
  }
}
