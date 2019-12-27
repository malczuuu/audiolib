package io.github.malczuuu.audiolib.common;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ObjectMapperFactory {

  public ObjectMapper getYamlObjectMapper() {
    return new ObjectMapper(new YAMLFactory())
        .setDefaultPropertyInclusion(Include.NON_NULL)
        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }
}
