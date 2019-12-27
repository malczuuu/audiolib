package io.github.malczuuu.audiolib.core.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAdapter implements ID3v2Adapter {

  private static final Logger log = LoggerFactory.getLogger(SimpleAdapter.class);

  private String album;
  private String title;
  private String track;
  private String artist;
  private String albumArtist;
  private String comment;
  private String genre;

  @Override
  public void setAlbum(String album) {
    this.album = album;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public void setTrack(String track) {
    this.track = track;
  }

  @Override
  public void setArtist(String artist) {
    this.artist = artist;
  }

  @Override
  public void setAlbumArtist(String albumArtist) {
    this.albumArtist = albumArtist;
  }

  @Override
  public void setComment(String comment) {
    this.comment = comment;
  }

  @Override
  public void setGenre(String genre) {
    this.genre = genre;
  }

  @Override
  public String getAlbum() {
    return album;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public String getTrack() {
    return track;
  }

  @Override
  public String getArtist() {
    return artist;
  }

  @Override
  public String getAlbumArtist() {
    return albumArtist;
  }

  @Override
  public String getComment() {
    return comment;
  }

  @Override
  public String getGenre() {
    return genre;
  }

  @Override
  public void save(String filename) {
    log.info("Saved {}", filename);
  }
}
