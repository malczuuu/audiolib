package io.github.malczuuu.audiolib.core;

import io.github.malczuuu.audiolib.common.ThrowableConsumer;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class MediaFilesProcessing {

  private final AlbumConfig albumConfig;
  private final List<Path> paths;
  private int counter = 0;

  public MediaFilesProcessing() {
    this(AlbumConfig.DEFAULT, Collections.emptyList());
  }

  private MediaFilesProcessing(AlbumConfig albumConfig, List<Path> paths) {
    this.albumConfig = albumConfig;
    this.paths = List.copyOf(paths);
  }

  // TODO Builder

  public MediaFilesProcessing withAlbumConfig(AlbumConfig albumConfig) {
    return new MediaFilesProcessing(albumConfig, paths);
  }

  public MediaFilesProcessing withPaths(List<Path> paths) {
    return new MediaFilesProcessing(albumConfig, paths);
  }

  public void run() {
    forEachFile(this::handleSingleFile);
  }

  private void forEachFile(ThrowableConsumer<Path> consumer) {
    paths.forEach(
        path -> {
          try {
            consumer.consume(path);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        });
  }

  private void handleSingleFile(Path path) throws Exception {
    ++counter;
    SingleMediaFileConfig mediaFileConfig =
        new SingleMediaFileConfig(albumConfig, counter, Constants.OUTPUT_DIRECTORY, paths.size());
    new SingleMediaFileHandler(mediaFileConfig).process(MediaFile.open(path));
  }
}
