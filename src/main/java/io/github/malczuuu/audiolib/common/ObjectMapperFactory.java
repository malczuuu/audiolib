package io.github.malczuuu.audiolib.common;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class ObjectMapperFactory {

  public ObjectMapper getObjectMapper() {
    return new ObjectMapper()
        .setDefaultPropertyInclusion(Include.NON_NULL)
        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }
}
