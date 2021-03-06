package io.github.malczuuu.audiolib.core;

import io.github.malczuuu.audiolib.core.adapter.ID3v2Adapter;
import io.github.malczuuu.audiolib.core.adapter.Mp3FileAdapter;
import java.io.IOException;
import java.nio.file.Path;

public class MediaFile implements ID3v2Adapter {

  public static MediaFile open(Path path) throws IOException {
    return new MediaFile(Mp3FileAdapter.open(path));
  }

  private final ID3v2Adapter adapter;

  public MediaFile(ID3v2Adapter adapter) {
    this.adapter = adapter;
  }

  @Override
  public void setAlbum(String album) {
    adapter.setAlbum(album);
  }

  @Override
  public void setTitle(String title) {
    adapter.setTitle(title);
  }

  @Override
  public void setTrack(String track) {
    adapter.setTrack(track);
  }

  @Override
  public void setArtist(String artist) {
    adapter.setArtist(artist);
  }

  @Override
  public void setAlbumArtist(String albumArtist) {
    adapter.setAlbumArtist(albumArtist);
  }

  @Override
  public void setComment(String comment) {
    adapter.setComment(comment);
  }

  @Override
  public void setGenre(String genre) {
    adapter.setGenre(genre);
  }

  @Override
  public String getAlbum() {
    return adapter.getAlbum();
  }

  @Override
  public String getTitle() {
    return adapter.getTitle();
  }

  @Override
  public String getTrack() {
    return adapter.getTrack();
  }

  @Override
  public String getArtist() {
    return adapter.getArtist();
  }

  @Override
  public String getAlbumArtist() {
    return adapter.getAlbumArtist();
  }

  @Override
  public String getComment() {
    return adapter.getComment();
  }

  @Override
  public String getGenre() {
    return adapter.getGenre();
  }

  @Override
  public void save(String filename) throws IOException {
    adapter.save(filename);
  }
}
