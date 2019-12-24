package io.github.malczuuu.audiolib.common;

public interface ThrowableConsumer<T> {

  void consume(T t) throws Exception;
}
