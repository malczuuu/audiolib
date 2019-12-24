package io.github.malczuuu.audiolib.core.adapter;

import java.io.IOException;

public interface ID3v2Adapter {

  void init();

  void setAlbum(String album);

  void setTitle(String title);

  void setTrack(String track);

  void setArtist(String artist);

  void setAlbumArtist(String albumArtist);

  void setComment(String description);

  void setGenre(String genre);

  String getAlbum();

  String getTitle();

  String getTrack();

  String getArtist();

  String getAlbumArtist();

  String getComment();

  String getGenre();

  void save() throws IOException;
}