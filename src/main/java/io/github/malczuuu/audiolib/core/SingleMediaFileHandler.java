package io.github.malczuuu.audiolib.core;

import io.github.malczuuu.audiolib.common.TrackNumberFormatter;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SingleMediaFileHandler {

  private static final Logger log = LoggerFactory.getLogger(SingleMediaFileHandler.class);

  private final SingleMediaFileConfig mediaFileConfig;

  public SingleMediaFileHandler(SingleMediaFileConfig mediaFileConfig) {
    this.mediaFileConfig = mediaFileConfig;
  }

  public void process(MediaFile file) throws IOException {
    file.setAlbum(postFormat(formatAlbum()));
    file.setTitle(postFormat(formatTitle()));
    file.setTrack(postFormat(Integer.toString(mediaFileConfig.getTrack())));
    file.setArtist(postFormat(mediaFileConfig.getArtist()));
    file.setAlbumArtist(postFormat(mediaFileConfig.getAlbumArtist()));
    file.setGenre(postFormat(Constants.AUDIOBOOK_GENRE));
    file.setComment(postFormat(mediaFileConfig.getDescription()));

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

  private String postFormat(String string) {
    return StringUtils.stripAccents(string);
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
    String result = mediaFileConfig.getBookTitle() + " #";
    result += trackNumberAsString();
    return result;
  }

  private String trackNumberAsString() {
    return new TrackNumberFormatter()
        .format(mediaFileConfig.getTracksTotal(), mediaFileConfig.getTrack());
  }
}
