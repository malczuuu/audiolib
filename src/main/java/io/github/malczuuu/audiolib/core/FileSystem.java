package io.github.malczuuu.audiolib.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileSystem {

  public List<Path> listOfMp3Files(Path location) throws IOException {
    return listFiles(location).stream()
        .filter(Files::isRegularFile)
        .filter(path -> path.toString().matches(Constants.MP3_EXPRESSION))
        .sorted(Comparator.comparing(Path::toString))
        .collect(Collectors.toList());
  }

  private List<Path> listFiles(Path location) throws IOException {
    return Files.list(location).collect(Collectors.toList());
  }

  public void createDirectory(Path directory) throws IOException {
    if (!Files.exists(directory)) {
      Files.createDirectory(directory);
    }
  }
}
