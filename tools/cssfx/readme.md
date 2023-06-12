Starting monitoring CSS changes in development is as simple as adding one line in your application code.

```
CSSFX.start()
```

Doing so CSSFX will start to track every CSS resource that will be declared on any scene or parent in your application. This monitoring will be active for all stages that your application will use.

### Mapping URIs to files on disk

CSSFX uses a functional interface `URIToPathConverter` (a function<String, Path> in fact) in order to be able to map CSS URIs to files on the disk.

By providing several default implementations CSSFX is expected to run for you out of the box, without changes.

CSSFX comes with converters for:

- Maven
- Gradle
- execution from jar file

By registering new converters, you can influence the way CSSFX resolves the files to monitor, see next paragraph for an example.

If you think that CSSFX is missing some default converters, please post a [new issue](https://github.com/McFoggy/cssfx/issues/new) or create a [pull request](https://github.com/McFoggy/cssfx/compare/).

### Converter example

Let's consider the following situation (sorry for the windows like path, you'll transform by yourself for other envs):

* my app is packaged in c:/jars/myapp.jar
* my sources are located in c:/projects/myapp/src/...

In order to support this setup, you could create your converter and use it in CSSFX

```java
URIToPathConverter myConverter = new URIToPathConverter() {
    @Override
    public Path convert(String uri) {
        Matcher m = Pattern.compile("jar:file:/.*\\.jar!/(.*\\.css)").matcher(uri);
        if (m.matches()) {
            final String sourceFile = m.replaceFirst("c:/projects/myapp/src/$1").replace('/', '\\');
            return Paths.get(sourceFile);
        }
        return null;
    }
};

CSSFX.addConverter(myConverter).start();
```

### Embedded with homemade configuration

If you need more control on how CSSFX will monitor your application and CSS changes, then you can use some extended functionalities of the `CSSFX` builder class.

There you will be able to:

- add/reset converters
- restrict monitoring on
    - one Stage
    - one Scene
    - one Node
    
### Logging in CSSFX

CSSFX comes with a mini logging framework.

CSSFX supports different properties to change default logging behavior

| System Property | Description |
|:----------:|:------------------|
|`cssfx.log`|activates CSSFX logging|
|`cssfx.log.level`|set the logging level to use, possible values `NONE ERROR WARN INFO DEBUG`, default is `INFO`|
|`cssfx.log.type`|set the type of "appender" to use, possible values `none console jul`, default is `console` |

You can also register your own LoggerFactory.

```java
CSSFXLogger.setLoggerFactory((loggerName) -> (level, message, args) -> {
    System.out.println("I log by myself, original message: " + String.format(message, args));
});
```