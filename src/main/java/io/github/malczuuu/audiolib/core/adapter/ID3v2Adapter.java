package io.github.malczuuu.audiolib.core.adapter;

import java.io.IOException;

public interface ID3v2Adapter {

  void setAlbum(String album);

  void setTitle(String title);

  void setTrack(String track);

  void setArtist(String artist);

  void setAlbumArtist(String albumArtist);

  void setComment(String comment);

  void setGenre(String genre);

  String getAlbum();

  String getTitle();

  String getTrack();

  String getArtist();

  String getAlbumArtist();

  String getComment();

  String getGenre();

  void save(String filename) throws IOException;
}
