# AudioLIB

[![Build Status](https://travis-ci.org/malczuuu/audiolib.svg?branch=master)](https://travis-ci.org/malczuuu/audiolib)

Organize and apply ID3v2 tags to your audiobook files.

## How to use?

Build project with `./gradlew install`.

In directory with audiobook MP3 files create `__directory.yaml` file with
following configuration.

```yaml
albums:
  - book_series: "{book_series_1}"
    book_title: "{book_title_1}"
    artist: "{artist_1}"
    album_artist: "{album_artist_1}"
    description: "{description_1}"
    input: "/path/to/input/directory/1/"
    output: "/path/to/output/directory/1/"

  - book_series: "{book_series_2}"
    book_title: "{book_title_2}"
    artist: "{artist_2}"
    album_artist: "{album_artist_2}"
    description: "{description_2}"
    input: "/path/to/input/directory/2/"
    output: "/path/to/output/directory/2/"
  # ...
```

Then call `{path_to_project_root}/build/install/audiolib/bin/audiolib` from
within directory of your MP3 files. Program will create `result/` directory
with properly formatted files.

| Property          | Assigned value                                |
|-------------------|-----------------------------------------------|
| `album`           | `${book_series}. ${title}`                    |
| `title`           | `${album} #${track}`                          |
| `track`           | number of file (ordered by filename)          |
| `artist`          | `${artist}`                                   |
| `album_artist`    | `${album_artist}`                             |
| `comment`         | `${description}`                              |
| `genre`           | `"Audiobook"`                                 |
| `filename`        | `${album} #${track}` (`title` without `#`)    |
