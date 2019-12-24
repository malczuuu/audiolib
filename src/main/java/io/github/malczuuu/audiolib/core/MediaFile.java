package io.github.malczuuu.audiolib.core;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MediaFile {

  private static final Logger log = LoggerFactory.getLogger(MediaFile.class);

  public static MediaFile open(Path path) throws IOException {
    try {
      return new MediaFile(new Mp3File(path));
    } catch (UnsupportedTagException | InvalidDataException e) {
      throw new IOException(e);
    }
  }

  private final Mp3File file;

  public MediaFile(Mp3File file) {
    this.file = file;
  }

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

  public void setAlbum(String album) {
    file.getId3v2Tag().setAlbum(album);
  }

  public void setTitle(String title) {
    file.getId3v2Tag().setTitle(title);
  }

  public void setTrack(String track) {
    file.getId3v2Tag().setTrack(track);
  }

  public void setArtist(String artist) {
    file.getId3v2Tag().setArtist(artist);
  }

  public void setAlbumArtist(String albumArtist) {
    file.getId3v2Tag().setAlbumArtist(albumArtist);
  }

  public void setDescription(String description) {
    file.getId3v2Tag().setComment(description);
  }

  public void setGenre(String genre) {
    file.getId3v2Tag().setGenreDescription(genre);
  }

  public String getAlbum() {
    return file.getId3v2Tag().getAlbum();
  }

  public String getTitle() {
    return file.getId3v2Tag().getTitle();
  }

  public String getTrack() {
    return file.getId3v2Tag().getTrack();
  }

  public String getArtist() {
    return file.getId3v2Tag().getArtist();
  }

  public String getAlbumArtist() {
    return file.getId3v2Tag().getAlbumArtist();
  }

  public String getDescription() {
    return file.getId3v2Tag().getComment();
  }

  public String getGenre() {
    return file.getId3v2Tag().getGenreDescription();
  }

  public void save() throws IOException {
    String filename = Constants.OUTPUT_DIRECTORY + getTitle().replaceAll("#", "");
    try {
      file.save(filename);
      log.info("Saved file='{}'", filename);
    } catch (NotSupportedException e) {
      log.error("Unable to save file='{}'", filename, e);
      throw new IOException(e);
    }
  }
}
