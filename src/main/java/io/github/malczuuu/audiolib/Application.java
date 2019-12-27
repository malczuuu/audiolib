package io.github.malczuuu.audiolib;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.malczuuu.audiolib.common.ObjectMapperFactory;
import io.github.malczuuu.audiolib.core.AlbumConfig;
import io.github.malczuuu.audiolib.core.AlbumProcessing;
import io.github.malczuuu.audiolib.core.Constants;
import java.io.IOException;
import java.util.List;

public class Application {

  public static void main(String[] args) throws Exception {

    ObjectMapper objectMapper = new ObjectMapperFactory().getYamlObjectMapper();

    List<AlbumConfig> albums =
        AlbumConfig.withObjectMapper(objectMapper).loadFromFile(Constants.CONFIG_FILE);

    albums.forEach(
        album -> {
          try {
            new AlbumProcessing(album).run();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
  }
}
