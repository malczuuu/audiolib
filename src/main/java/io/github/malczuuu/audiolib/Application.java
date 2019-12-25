package io.github.malczuuu.audiolib;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.malczuuu.audiolib.common.ObjectMapperFactory;
import io.github.malczuuu.audiolib.core.AlbumConfig;
import io.github.malczuuu.audiolib.core.Constants;
import io.github.malczuuu.audiolib.core.FileSystem;
import io.github.malczuuu.audiolib.core.MediaFilesProcessing;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Application {

  private final AlbumConfig albumConfig;
  private final FileSystem fileSystem;

  public Application(AlbumConfig albumConfig, FileSystem fileSystem) {
    this.albumConfig = albumConfig;
    this.fileSystem = fileSystem;
  }

  void run() throws Exception {
    List<Path> paths = fileSystem.listOfMp3Files(Paths.get(Constants.INPUT_DIRECTORY));
    fileSystem.createDirectory(Paths.get(Constants.OUTPUT_DIRECTORY));
    new MediaFilesProcessing().withAlbumConfig(albumConfig).withPaths(paths).run();
  }

  public static void main(String[] args) throws Exception {

    FileSystem fileSystem = new FileSystem();
    ObjectMapper objectMapper = new ObjectMapperFactory().getObjectMapper();

    AlbumConfig albumConfig =
        AlbumConfig.withObjectMapper(objectMapper).withFileSystem(fileSystem).loadFromFile(Constants.CONFIG_FILE);

    new Application(albumConfig, fileSystem).run();
  }
}
