package io.github.malczuuu.audiolib;

import io.github.malczuuu.audiolib.api.MediaFile;
import io.github.malczuuu.audiolib.common.TrackNumberFormatter;

public class AudiolibProcessor implements ApplicationConstants {

  private final AudiolibProcessProps props;

  public AudiolibProcessor(AudiolibProcessProps props) {
    this.props = props;
  }

  public void process(MediaFile file) throws Exception {
    file.prepare();
    file.setAlbum(formatAlbum());
    file.setTitle(formatTitle());
    file.setTrack(Integer.toString(props.getTrack()));
    file.setArtist(props.getArtist());
    file.setAlbumArtist(props.getAlbumArtist());
    file.setGenre(AUDIOBOOK_GENRE);
    file.setDescription(props.getDescription());
    file.getPersistence().persist(file);
  }

  private String formatAlbum() {
    String result = "";
    if (props.getBookSeries() != null && !props.getBookSeries().isEmpty()) {
      result += props.getBookSeries() + ". ";
    }
    result += props.getBookTitle();
    return result;
  }

  private String formatTitle() {
    String result = formatAlbum() + " #";
    result += trackNumberAsString();
    return result;
  }

  private String trackNumberAsString() {
    return new TrackNumberFormatter().format(props.getTracksTotal(), props.getTrack());
  }
}
