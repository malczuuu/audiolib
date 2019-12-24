package io.github.malczuuu.audiolib.api;

public interface MediaFile {

  void prepare();

  void setAlbum(String album);

  void setTitle(String title);

  void setTrack(String track);

  void setArtist(String artist);

  void setAlbumArtist(String albumArtist);

  void setDescription(String description);

  void setGenre(String genre);

  String getAlbum();

  String getTitle();

  String getTrack();

  String getArtist();

  String getAlbumArtist();

  String getDescription();

  String getGenre();

  MediaFilePersistence getPersistence();
}
