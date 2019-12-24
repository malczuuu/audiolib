package io.github.malczuuu.audiolib.core;

import io.github.malczuuu.audiolib.common.ThrowableConsumer;
import java.nio.file.Path;
import java.util.List;

public class MediaFilesProcessing {

  private final AlbumConfig albumConfig;
  private final List<Path> paths;
  private int counter = 0;

  public MediaFilesProcessing(AlbumConfig albumConfig, List<Path> paths) {
    this.albumConfig = albumConfig;
    this.paths = List.copyOf(paths);
  }

  public void run() {
    forEachFile(this::onValidPath);
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

  private void onValidPath(Path path) throws Exception {
    ++counter;
    SingleMediaFileConfig mediaFileConfig =
        new SingleMediaFileConfig(albumConfig, counter, Constants.OUTPUT_DIRECTORY, paths.size());
    new SingleMediaFileHandler(mediaFileConfig).process(MediaFile.open(path));
  }
}
