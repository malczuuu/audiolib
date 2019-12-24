package io.github.malczuuu.audiolib.core;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import io.github.malczuuu.audiolib.core.adapter.ID3v2Adapter;
import io.github.malczuuu.audiolib.core.adapter.Mp3FileAdapter;
import java.io.IOException;
import java.nio.file.Path;

public class MediaFile {

  public static MediaFile open(Path path) throws IOException {
    try {
      return new MediaFile(new Mp3FileAdapter(new Mp3File(path)));
    } catch (UnsupportedTagException | InvalidDataException e) {
      throw new IOException(e);
    }
  }

  private final ID3v2Adapter adapter;

  public MediaFile(ID3v2Adapter adapter) {
    this.adapter = adapter;
  }

  public void prepare() {
    adapter.init();
  }

  public void setAlbum(String album) {
    adapter.setAlbum(album);
  }

  public void setTitle(String title) {
    adapter.setTitle(title);
  }

  public void setTrack(String track) {
    adapter.setTrack(track);
  }

  public void setArtist(String artist) {
    adapter.setArtist(artist);
  }

  public void setAlbumArtist(String albumArtist) {
    adapter.setAlbumArtist(albumArtist);
  }

  public void setDescription(String description) {
    adapter.setComment(description);
  }

  public void setGenre(String genre) {
    adapter.setGenre(genre);
  }

  public String getAlbum() {
    return adapter.getAlbum();
  }

  public String getTitle() {
    return adapter.getTitle();
  }

  public String getTrack() {
    return adapter.getTrack();
  }

  public String getArtist() {
    return adapter.getArtist();
  }

  public String getAlbumArtist() {
    return adapter.getAlbumArtist();
  }

  public String getDescription() {
    return adapter.getComment();
  }

  public String getGenre() {
    return adapter.getGenre();
  }

  public void save() throws IOException {
    adapter.save();
  }
}
