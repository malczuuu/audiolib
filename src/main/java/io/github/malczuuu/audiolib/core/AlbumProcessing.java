package io.github.malczuuu.audiolib.core;

import io.github.malczuuu.audiolib.common.TrackNumberFormatter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlbumProcessing {

  private static final Logger log = LoggerFactory.getLogger(AlbumProcessing.class);

  private final AlbumConfig config;

  public AlbumProcessing(AlbumConfig config) {
    this.config = config;
  }

  public void run() throws IOException {
    List<Path> paths =
        Files.list(Paths.get(config.getInput()))
            .filter(Files::isRegularFile)
            .filter(f -> f.toAbsolutePath().toString().matches(Constants.MP3_EXPRESSION))
            .collect(Collectors.toList());

    int counter = 0;

    List<String> outputSegments = new ArrayList<>();
    outputSegments.add(config.getOutput());
    if (config.getBookSeries() != null) {
      outputSegments.add(postFormat(config.getBookSeries()));
    }
    outputSegments.add(postFormat(config.getBookTitle()));

    Path output = Paths.get(String.join("/", outputSegments));
    Files.createDirectories(output);
    for (Path path : paths) {
      ++counter;
      MediaFile file = MediaFile.open(path);
      process(file, counter, paths.size());
      file.save(output.toString() + "/" + file.getTitle() + ".mp3");
    }
  }

  public void process(MediaFile file, int track, int total) throws IOException {
    file.setAlbum(postFormat(formatAlbum()));
    file.setTitle(postFormat(formatTitle(track, total)));
    file.setTrack(postFormat(Integer.toString(track)));
    file.setArtist(postFormat(config.getArtist()));
    file.setAlbumArtist(postFormat(config.getAlbumArtist()));
    file.setGenre(postFormat(Constants.AUDIOBOOK_GENRE));
    file.setComment(postFormat(config.getDescription()));

    log.debug(
        "Saving media file with album='{}', title='{}', track={}, artist='{}', album_artist='{}', genre='{}', comment='{}'",
        file.getAlbum(),
        file.getTitle(),
        file.getTrack(),
        file.getArtist(),
        file.getAlbumArtist(),
        file.getGenre(),
        file.getComment());
  }

  private String postFormat(String string) {
    return StringUtils.stripAccents(string);
  }

  private String formatAlbum() {
    String result = "";
    if (config.getBookSeries() != null && !config.getBookSeries().isEmpty()) {
      result += config.getBookSeries() + ". ";
    }
    result += config.getBookTitle();
    return result;
  }

  private String formatTitle(int track, int total) {
    String result = config.getBookTitle() + " #";
    result += trackNumberAsString(track, total);
    return result;
  }

  private String trackNumberAsString(int track, int total) {
    return new TrackNumberFormatter().format(total, track);
  }
}
