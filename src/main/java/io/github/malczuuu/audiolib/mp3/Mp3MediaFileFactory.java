package io.github.malczuuu.audiolib.mp3;

import com.mpatric.mp3agic.Mp3File;
import io.github.malczuuu.audiolib.api.MediaFile;
import io.github.malczuuu.audiolib.api.MediaFileFactory;
import java.nio.file.Path;

public class Mp3MediaFileFactory implements MediaFileFactory {

  private final Path path;

  public Mp3MediaFileFactory(Path path) {
    this.path = path;
  }

  @Override
  public MediaFile getMediaFile() throws Exception {
    return new Mp3MediaFile(new Mp3File(path));
  }
}
