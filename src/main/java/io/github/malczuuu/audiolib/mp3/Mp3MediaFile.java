package io.github.malczuuu.audiolib.mp3;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.Mp3File;
import io.github.malczuuu.audiolib.api.MediaFile;
import io.github.malczuuu.audiolib.api.MediaFilePersistence;

public class Mp3MediaFile implements MediaFile {

  private final Mp3File file;

  public Mp3MediaFile(Mp3File file) {
    this.file = file;
  }

  @Override
  public void prepare() {
    resetId3v1Tag();
    resetCustomTag();
    resetId3v2Tag();
  }

  private void resetId3v1Tag() {
    file.removeId3v1Tag();
  }

  private void resetCustomTag() {
    file.removeCustomTag();
  }

  private void resetId3v2Tag() {
    ID3v2 id3v2 = file.getId3v2Tag();

    ID3v24Tag tag = new ID3v24Tag();
    if (id3v2 != null) {
      tag.setAlbum(id3v2.getAlbum());
      tag.setTitle(id3v2.getTitle());
      tag.setTrack(id3v2.getTrack());
      tag.setArtist(id3v2.getArtist());
      tag.setAlbumArtist(id3v2.getAlbumArtist());
      tag.setComment(id3v2.getComment());
    }

    file.setId3v2Tag(tag);
  }

  @Override
  public void setAlbum(String album) {
    file.getId3v2Tag().setAlbum(album);
  }

  @Override
  public void setTitle(String title) {
    file.getId3v2Tag().setTitle(title);
  }

  @Override
  public void setTrack(String track) {
    file.getId3v2Tag().setTrack(track);
  }

  @Override
  public void setArtist(String artist) {
    file.getId3v2Tag().setArtist(artist);
  }

  @Override
  public void setAlbumArtist(String albumArtist) {
    file.getId3v2Tag().setAlbumArtist(albumArtist);
  }

  @Override
  public void setDescription(String description) {
    file.getId3v2Tag().setComment(description);
  }

  @Override
  public void setGenre(String genre) {
    file.getId3v2Tag().setGenreDescription(genre);
  }

  @Override
  public String getAlbum() {
    return file.getId3v2Tag().getAlbum();
  }

  @Override
  public String getTitle() {
    return file.getId3v2Tag().getTitle();
  }

  @Override
  public String getTrack() {
    return file.getId3v2Tag().getTrack();
  }

  @Override
  public String getArtist() {
    return file.getId3v2Tag().getArtist();
  }

  @Override
  public String getAlbumArtist() {
    return file.getId3v2Tag().getAlbumArtist();
  }

  @Override
  public String getDescription() {
    return file.getId3v2Tag().getComment();
  }

  @Override
  public String getGenre() {
    return file.getId3v2Tag().getGenreDescription();
  }

  @Override
  public MediaFilePersistence getPersistence() {
    return new Mp3MediaFilePersistence();
  }

  Mp3File getFile() {
    return file;
  }
}
