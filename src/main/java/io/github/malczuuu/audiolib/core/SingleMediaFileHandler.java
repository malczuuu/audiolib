package io.github.malczuuu.audiolib.core;

import io.github.malczuuu.audiolib.common.TrackNumberFormatter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleMediaFileHandler {

  private static final Logger log = LoggerFactory.getLogger(SingleMediaFileHandler.class);

  private final SingleMediaFileConfig mediaFileConfig;

  public SingleMediaFileHandler(SingleMediaFileConfig mediaFileConfig) {
    this.mediaFileConfig = mediaFileConfig;
  }

  public void process(MediaFile file) throws IOException {
    file.init();
    file.setAlbum(formatAlbum());
    file.setTitle(formatTitle());
    file.setTrack(Integer.toString(mediaFileConfig.getTrack()));
    file.setArtist(mediaFileConfig.getArtist());
    file.setAlbumArtist(mediaFileConfig.getAlbumArtist());
    file.setGenre(Constants.AUDIOBOOK_GENRE);
    file.setComment(mediaFileConfig.getDescription());

    log.debug(
        "Saving media file with album='{}', title='{}', track={}, artist='{}', album_artist='{}', genre='{}', comment='{}'",
        file.getAlbum(),
        file.getTitle(),
        file.getTrack(),
        file.getArtist(),
        file.getAlbumArtist(),
        file.getGenre(),
        file.getComment());

    file.save();
  }

  private String formatAlbum() {
    String result = "";
    if (mediaFileConfig.getBookSeries() != null && !mediaFileConfig.getBookSeries().isEmpty()) {
      result += mediaFileConfig.getBookSeries() + ". ";
    }
    result += mediaFileConfig.getBookTitle();
    return result;
  }

  private String formatTitle() {
    String result = formatAlbum() + " #";
    result += trackNumberAsString();
    return result;
  }

  private String trackNumberAsString() {
    return new TrackNumberFormatter()
        .format(mediaFileConfig.getTracksTotal(), mediaFileConfig.getTrack());
  }
}
