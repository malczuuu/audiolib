package io.github.malczuuu.audiolib;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.malczuuu.audiolib.api.MediaFileFactory;
import io.github.malczuuu.audiolib.common.ObjectMapperFactory;
import io.github.malczuuu.audiolib.common.ThrowableConsumer;
import io.github.malczuuu.audiolib.mp3.Mp3MediaFileFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class AudiolibApplication implements ApplicationConstants {

  private final AudiolibConfig config;

  private int total = 0;
  private int counter = 0;

  public AudiolibApplication(AudiolibConfig config) {
    this.config = config;
  }

  void run() throws Exception {
    requireOutputDirectory();
    forEachFile(this::onValidPath);
  }

  private void requireOutputDirectory() throws IOException {
    if (!Files.exists(Path.of(OUTPUT_DIRECTORY))) {
      Files.createDirectory(Path.of(OUTPUT_DIRECTORY));
    }
  }

  void forEachFile(ThrowableConsumer<Path> consumer) throws IOException {
    List<Path> paths =
        Files.list(Paths.get(INPUT_DIRECTORY))
            .filter(Files::isRegularFile)
            .filter(path -> path.toString().matches(MP3_EXPRESSION))
            .sorted(this::orderByFilename)
            .collect(Collectors.toList());

    total = paths.size();

    paths.forEach(
        path -> {
          try {
            consumer.consume(path);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        });
  }

  private int orderByFilename(Path a, Path b) {
    return a.toString().compareTo(b.toString());
  }

  private void onValidPath(Path path) throws Exception {
    ++counter;
    MediaFileFactory factory = new Mp3MediaFileFactory(path);
    AudiolibProcessProps props = new AudiolibProcessProps(config, counter, OUTPUT_DIRECTORY, total);
    new AudiolibProcessor(props).process(factory.getMediaFile());
  }

  public static void main(String[] args) throws Exception {
    String configFileContent = readConfigFile();
    ObjectMapper mapper = new ObjectMapperFactory().getObjectMapper();
    AudiolibConfig config = mapper.readValue(configFileContent, AudiolibConfig.class);
    new AudiolibApplication(config).run();
  }

  private static String readConfigFile() throws IOException {
    return String.join("", Files.readAllLines(Path.of(CONFIG_FILE)));
  }
}
