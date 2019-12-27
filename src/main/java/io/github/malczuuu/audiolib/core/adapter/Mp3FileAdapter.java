package io.github.malczuuu.audiolib.core.adapter;

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

public class Mp3FileAdapter implements ID3v2Adapter {

  public static Mp3FileAdapter open(Path path) throws IOException {
    try {
      Mp3File file = new Mp3File(path);
      Mp3FileAdapter adapter = new Mp3FileAdapter(file);
      adapter.init();
      return adapter;
    } catch (UnsupportedTagException | InvalidDataException e) {
      throw new IOException(e);
    }
  }

  private static final Logger log = LoggerFactory.getLogger(Mp3FileAdapter.class);

  private final Mp3File file;

  private Mp3FileAdapter(Mp3File file) {
    this.file = file;
  }

  private void init() {
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
    getId3v2Tag().setAlbum(album);
  }

  private ID3v2 getId3v2Tag() {
    return file.getId3v2Tag();
  }

  @Override
  public void setTitle(String title) {
    getId3v2Tag().setTitle(title);
  }

  @Override
  public void setTrack(String track) {
    getId3v2Tag().setTrack(track);
  }

  @Override
  public void setArtist(String artist) {
    getId3v2Tag().setArtist(artist);
  }

  @Override
  public void setAlbumArtist(String albumArtist) {
    getId3v2Tag().setAlbumArtist(albumArtist);
  }

  @Override
  public void setComment(String comment) {
    getId3v2Tag().setComment(comment);
  }

  @Override
  public void setGenre(String genre) {
    getId3v2Tag().setGenreDescription(genre);
  }

  @Override
  public String getAlbum() {
    return getId3v2Tag().getAlbum();
  }

  @Override
  public String getTitle() {
    return getId3v2Tag().getTitle();
  }

  @Override
  public String getTrack() {
    return getId3v2Tag().getTrack();
  }

  @Override
  public String getArtist() {
    return getId3v2Tag().getArtist();
  }

  @Override
  public String getAlbumArtist() {
    return getId3v2Tag().getAlbumArtist();
  }

  @Override
  public String getComment() {
    return getId3v2Tag().getComment();
  }

  @Override
  public String getGenre() {
    return getId3v2Tag().getGenreDescription();
  }

  @Override
  public void save(String filename) throws IOException {
    try {
      file.save(filename);
      log.info("Saved file='{}'", filename);
    } catch (NotSupportedException e) {
      log.error("Unable to save file='{}'", filename, e);
      throw new IOException(e);
    }
  }
}
