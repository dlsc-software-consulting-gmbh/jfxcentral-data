# JFX-Central Data Repository

To add data to this repository please follow these steps:

1. Fork the repository
2. Add your data
3. Submit a pull request for the `staging` branch

> Don't worry too much about breaking things. Unit tests are in place to verify
> the validity of the data. After those have passed your edits will be reviewed
> by a human being, yours truly. Once the review is done your pull request will
> be merged. Please be aware that there is no guarantee that the information provided 
> by your pull request will appear immediately after merging. The website will be 
> updated only when explicitly requested by an administrator.
 
## General Concepts

This repository contains the data for the following model objects:

- `Blog`
- `Book`
- `Company`
- `Download`
- `Library`
- `News`
- `Person`
- `RealWorldApp`
- `Tool`
- `Tutorial`
- `Video`
- `Tip`

Instances of these types can be registered in JSON files in subdirectories that
match the types of the model objects:

- Blogs inside `blogs/blogs.json`
- Books inside `books/books.json`
- Companies inside `companies/companies.json`
- Downloads inside `downloads/downloads.json`
- Libraries inside `libraries/libraries.json`
- News inside `news/news.json`
- People inside `people/people.json`
- Real World Applications inside `realworld/realworld.json`
- Tools inside `tools/tools.json`
- Tutorials inside `tutorials/tutorials.json`
- Videos inside `videos/videos.json`
- Tips inside `tips/tips.json`

The design goal for these JSON files was to keep them as simple as possible. One can see that
a specific directory exists for each model object ***type***. Inside those directories one can find another
level of directories where each directory represents a model object ***instance***. The name of the
directory has to match the ID given to the ***instance*** inside the JSON index files. For example:
the book *"Pro JavaFX 9"* uses the ID `projfx9`. This means we can find a directory called 
`books/projfx9`.

Inside most of these directories we can then find a file called `readme.md` which allows
you to add information (in markdown syntax) about the model object ***instance***. Depending on the
***type*** of the model object additional information (optional or required) might be needed.

### Linking

Any model object can be linked to any other model object of any type. The class ModelObject
contains the following fields that are common to all types:

```java    
private String id; // the id == directory name
private LocalDate createdOn; // when was the entry initially added
private LocalDate modifiedOn; // when was it changed
private String tags; // some tags to enhance global search
private boolean hide; // maybe hide the entry for now

// Linking Options
private List<String> personIds = new ArrayList<>();
private List<String> tutorialIds = new ArrayList<>();
private List<String> toolIds = new ArrayList<>();
private List<String> libraryIds = new ArrayList<>();
private List<String> bookIds = new ArrayList<>();
private List<String> companyIds = new ArrayList<>();
private List<String> downloadIds = new ArrayList<>();
private List<String> videoIds = new ArrayList<>();
private List<String> appIds = new ArrayList<>();
private List<String> blogIds = new ArrayList<>();`
private List<String> tipIds = new ArrayList<>();`
```

Example: to link the library *FlexGanttFX* to the person *Dirk Lemmermann* we can add the following
fragment to the `libraries.json` file.

```json
{
    "id": "flexganttfx",
    "title": "FlexGanttFX",
    "personIds" : [
      "d.lemmermann"
    ]
}
```

To also add YouTube videos (those need to be registered inside `videos/videos.json`) to the library we can then write:

```json
{
    "id": "flexganttfx",
    "title": "FlexGanttFX",
    "personIds" : [
      "d.lemmermann"
    ],
    "videoIds": [
      "u09iklm65",
      "kl889abV8"
    ]
}
```

The other way around is also possible. We could add the library ID `flexganttfx` to the person
*Dirk Lemmermann* inside the file `people.json`.

```json
{
    "id": "d.lemmermann",
    "name": "Dirk Lemmermann",
    "libraryIds" : [
      "flexganttfx"
    ]
}
```

Ideally you take a look at the already existing data in those JSON files. They will pretty much
tell you what to do.

### Examples

Follow these instructions for the different types of data:

- [Adding a person](#adding-a-person) 
- [Adding a library](#adding-a-library)

### Adding a Person

1. Add an entry to the file `people/people.json`, create a unique `ID` in the entry.
2. Create a new directory inside the `people` directory with the name equal to the `ID` field you created in the previous step (e.g.: ID = `d.lemmermann` -> `people/d.lemmermann`).
3. To the directory created in step 2: add an image file called `photo.jpeg` with the person's photo (ideally download the LinkedIn profile photo, it has the right format and size).
4. To the directory created in step 2: add a file called `readme.md` and add a short bio about the person (I copied the Twitter bios by default).
5. If the person entry inside the file `people/people.json` references other entities (for example a library) then make sure to also add those to the repository.

### Adding a Library

1. Add an entry to the file `libraries/libraries.json`, create a unique `ID` in the entry.
2. Create a new directory inside the `libraries` directory with the name equal to the `ID` field you created in the previous step (e.g.: ID = `controlsfx` -> `libraries/controlsfx`).
3. To the directory created in step 2: add an image file called `logo.png` with the library's icon (optional).
4. To the directory created in step 2: add a file called `readme.md` and add a "getting started" documentation or whatever you deem most important to know about the library.
5. To the directory created in step 2: add (or copy) the file `info.json` and list screenshots or videos (those also need to be in the same directory).
5. If the library entry inside the file `libraries/libraries.json` references other entities (for example a video or a tutorial) then make sure to also add those to the repository.
