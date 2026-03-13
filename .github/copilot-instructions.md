# JFX-Central Data Repository — Copilot Instructions

## What This Repo Is

Content/data repository for [jfx-central.com](https://www.jfx-central.com), a community hub for the JavaFX ecosystem. It stores structured data (JSON + Markdown + images) for libraries, people, tools, books, news, tutorials, blogs, videos, and more. A Maven-based Java project loads and validates all data via unit tests.

## Build & Test

Use the Maven wrapper:

```bash
./mvnw clean package       # Build the data model JAR
./mvnw test                # Run all tests (data validation + image checks + RSS)
./mvnw -pl . -Dtest=DataRepositoryTest test   # Run a single test class
```

Tests run headless (TestFX) and validate data integrity — they catch missing files, broken links between entries, and malformed JSON. CI runs on every push via `.github/workflows/data-validation.yml`.

## Architecture

The repository has two layers:

1. **Data layer** — directories of JSON + Markdown + images, one subdirectory per entry.
2. **Java model layer** (`src/main/java/com/dlsc/jfxcentral/data/`) — loads and exposes data to the website.

Key Java classes:
- `DataRepository` — singleton that reads all JSON index files and provides query methods.
- `ImageManager` — resolves image file paths for all model types (logos, photos, banners).
- `RSSManager` — parses RSS/Atom feeds linked from entries.
- `model/ModelObject` — base class for all 14+ entity types; contains all cross-linking ID lists.

## Data Conventions

### Directory ↔ JSON ID Rule (Critical)
Every entity has a unique ID. The **directory name must exactly match the `id` field** in the JSON index file.

```
libraries/controlsfx/   ←→   { "id": "controlsfx", ... } in libraries/libraries.json
people/d.lemmermann/    ←→   { "id": "d.lemmermann", ... } in people/people.json
```

### JSON Index Files
Each type has one flat JSON array file:

| Type | Index file |
|------|-----------|
| Libraries | `libraries/libraries.json` |
| People | `people/people.json` |
| Tools | `tools/tools.json` |
| Books | `books/books.json` |
| News | `news/news.json` |
| Tutorials | `tutorials/tutorials.json` |
| Blogs | `blogs/blogs.json` |
| Videos | `videos/videos.json` |
| Downloads | `downloads/downloads.json` |
| Utilities | `utilities/utilities.json` |
| Companies | `companies/companies.json` |
| Real-world apps | `realworld/realworld.json` |
| Tips | `tips/tips.json` |
| Links of the week | `links/links.json` |

### Cross-Linking
Any entity can link to any other entity via ID lists in the JSON. Linking is **bidirectional by convention** but only needs to be declared on one side:

```json
{ "id": "flexganttfx", "personIds": ["d.lemmermann"], "videoIds": ["u09iklm65"] }
```

Available link fields (from `ModelObject`): `personIds`, `libraryIds`, `toolIds`, `tutorialIds`, `bookIds`, `videoIds`, `blogIds`, `companyIds`, `downloadIds`, `appIds`, `utilityIds`, `tipIds`, `linksOfTheWeekIds`.

### Per-Entry Directory Contents

**People** (`people/<id>/`):
- `readme.md` — short bio (required)
- `photo.jpeg` — profile photo, LinkedIn dimensions preferred (required)

**Libraries** (`libraries/<id>/`):
- `readme.md` — getting-started or overview content (required)
- `logo.png` — library logo (optional)
- `info.json` — image gallery metadata with `{ "images": [{ "path", "title", "description" }] }` (optional)
- Screenshot PNGs referenced by `info.json`

**News** (`news/<YYYY-MM-DD-slug>/`):
- `text.md` — article content (required)
- `banner.jpg` — featured image (optional)

**Links of the week** (`links/YYYY/YYYY-MM/YYYY-MM-DD/`):
- `readme.md` — weekly link collection

**Tools / Utilities / Downloads / Books / Tutorials** all follow the same pattern: `readme.md` + optional `logo.png` or `screenshot.jpg`.

### Common JSON Fields

All entries support these fields:

```json
{
  "id": "unique-id",
  "name": "Display Name",
  "summary": "One-line description",
  "description": "Longer description",
  "tags": "comma, separated, search, tags",
  "createdOn": "YYYY-MM-DD",
  "modifiedOn": "YYYY-MM-DD",
  "hide": false
}
```

### Image Naming Conventions

| File | Used for |
|------|----------|
| `logo.png` | Library / tool / utility logo |
| `photo.jpeg` | Person profile photo |
| `banner.jpg` | News article banner |
| `screenshot.jpg` | App / tool screenshot |
| `icon.png` | Blog icon |
| `page.png` / `page-small.png` | Blog page preview |

### Adding a New Entry (Checklist)

1. Add a JSON object with a unique `id` to the appropriate index file.
2. Create a directory with that same `id` under the type's folder.
3. Add `readme.md` (and `photo.jpeg` for people, `logo.png` for libraries/tools if available).
4. If linking to other entities, add the IDs to the appropriate `*Ids` list.
5. Ensure all referenced IDs actually exist in their respective index files.
6. Run `./mvnw test` to validate — the test suite will catch missing files and broken references.
