# JFX Central data

Content repository for [JFX Central](https://www.jfx-central.com). Each top-level folder (`realworld`, `videos`, `libraries`, `people`, `links`, ...) holds JSON metadata and/or markdown per item.

## Adding a "Real World" application

1. Add an entry to `realworld/realworld.json` (`id`, `name`, `tags`, `url`, `summary`, `domain`, `description`, `createdOn`, `modifiedOn`, optional `personIds`, `libraryIds`, `videoIds`).
2. Create `realworld/<id>/readme.md` plus images: `banner.jpg` (1024x697), `large.jpg` (960x540), `small.jpg` (288x162) and `screen1.jpg`... (960x540), referenced from the readme as `![Screen 1](screen1.jpg)`.

## YouTube videos

Embed a video in a readme with `![alt](youtube:VIDEO_ID)` (e.g. `![Barcodify Video](youtube:IZ7fTHC3rBM)`). Optionally, a video can also be registered in `videos/videos.json` and linked via `videoIds` / `realworldIds`; don't do both for the same video, or it shows twice.

## Weekly links (`links/<year>/<year-month>/<date>/readme.md`)

Sections: Core, SceneBuilder, Applications, Components/Libraries/Tools, Podcasts/Videos/Books, Conferences, Tutorials, Miscellaneous, JFX Central. Format: `* **Author** shared/published [Title](url) ...: short description.` Link only the item's name, never nest links. Don't invent facts; use only what the submitter or source states.

## Conventions

- Dates are `YYYY-MM-DD`. Validate JSON after editing (`python3 -c "import json;json.load(open('file.json'))"`).
- Only commit when asked.
