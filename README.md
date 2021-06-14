# JFX-Central Data Repository

To add data to this repository please follow these steps:

1. Fork the repository
2. Add your data
3. Submit a pull request for the `staging` branch

Follow these instructions for the different types of data:

- [Adding a person](#adding-a-person) 
- [Adding a library](#adding-a-library)
- [Adding a book](#adding-a-book)

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

### Adding a Book
