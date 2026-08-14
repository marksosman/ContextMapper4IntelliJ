# DDD Context Mapper
by [marksosman](https://github.com/marksosman)

This plug-in is IntelliJ Idea support for the Context Mapper, specifically for\
Domain-Driven Design context mapping.

Currently in early development.

### Testing

To check that the plug-in is available in a sandbox, 
simply run `./gradlew runIde` in your terminal.\
This will simulate a sandbox IntelliJ IDE and you can see the plug-in in the list of downloaded plug-ins.

**Requirements**

- **JDK 21** — the IntelliJ Platform 2025.2 targets Java 21. Newer JDKs will fail the build.
- IntelliJ IDEA 2025.2 or newer.

If your default JDK isn't 21, point Gradle at one in `~/.gradle/gradle.properties`:

    org.gradle.java.home=/path/to/jdk-21

On macOS, `/usr/libexec/java_home -V` lists installed JDKs.