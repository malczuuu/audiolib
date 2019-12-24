package io.github.malczuuu.audiolib.mp3;

import com.mpatric.mp3agic.Mp3File;
import io.github.malczuuu.audiolib.ApplicationConstants;
import io.github.malczuuu.audiolib.api.MediaFile;
import io.github.malczuuu.audiolib.api.MediaFilePersistence;

public class Mp3MediaFilePersistence implements MediaFilePersistence, ApplicationConstants {

  @Override
  public void persist(MediaFile file) throws Exception {
    if (file instanceof Mp3MediaFile) {
      persistImpl((Mp3MediaFile) file);
    } else {
      throw new IllegalArgumentException("Only Mp3MediaFile instances are supported");
    }
  }

  private void persistImpl(Mp3MediaFile file) throws Exception {
    Mp3File raw = file.getFile();
    raw.save(OUTPUT_DIRECTORY + file.getTitle().replaceAll("#", ""));
  }
}
