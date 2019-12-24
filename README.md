# AudioLIB

[![Build Status](https://travis-ci.org/malczuuu/audiolib.svg?branch=master)](https://travis-ci.org/malczuuu/audiolib)

Organize and apply ID3v2 tags to your audiobook files.

## How to use?

Build project with `./gradlew install`.

In directory with audiobook MP3 files create `__directory.json` file with
following configuration.

```json
{
  "book_series": "{book_series}",
  "book_title": "{book_title}",
  "artist": "{artist}",
  "album_artist": "{album_artist}",
  "description": "{description}"
}
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
