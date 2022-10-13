# Spring Security Third Edition

Spring Security 3/e 스프링 시큐리티를 이용한 애플리케이션 보안

# Chapter 1 Anatomy of an Unsafe Application

## Security audit

Jim Bob Circle Pants Online Calendar (JBCPCalendar.com)


# Additional Reference Material

## Getting started with the JBCP calendar sample code

JDK 8 from Oracle's website [http://www.oracle.com/technetwork/java/javase/downloads/index.html](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
Java SE 8(8u202 and earlier) [Java SE Development Kit 8u151](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)

### Gradle Build Tool

getting Gradle locally at [https://gradle.org/install](https://gradle.org/install)

Gradle wrapper at [https://docs.gradle.org/current/userguide/gradle_wrapper.html](https://docs.gradle.org/4.3/userguide/gradle_wrapper.html)

```
> set Path=%JAVA_HOME%\bin;%Path%
> cd jbcpcalendar
> gradlew init
.
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── settings.gradle
```

### Gradle IDE plugins
```
$ vi build.gradle

allprojects {
    apply plugin: "java"
    apply plugin: "eclipse"
    apply plugin: "idea"
}
```
```
$ .gradlew idea
```
```
C:\jbcpcalendar> gradlew.bat idea
```
IntelliJ project files are created with the idea task, and STS or any Eclipse-based IDE’s project files are created with the eclipse task.

[IntelliJ Plugin](https://docs.gradle.org/current/userguide/idea_plugin.html)

[Eclipse Plugin](https://docs.gradle.org/current/userguide/eclipse_plugin.html)


### IntelliJ IDEA

IntelliJ IDEA from Jet Brains [(https://www.jetbrains.com/idea)](https://www.jetbrains.com/idea)

IntelliJ IDEA Version 2017.3 [(https://www.jetbrains.com/idea/download/other.html)](https://www.jetbrains.com/idea/download/other.html)

**IntelliJ IDEA Ultimate**

[2017.3.7 - Linux without JBR (tar.gz)](https://download.jetbrains.com/idea/ideaIU-2017.3.7-no-jdk.tar.gz)

[2017.3.7 - Linux x86_64 (tar.gz)](https://download.jetbrains.com/idea/ideaIU-2017.3.7.tar.gz)

[2017.3.7 - Windows x64 (exe)](https://download.jetbrains.com/idea/ideaIU-2017.3.7.exe)

[2017.3.7 - Windows x64 ZIP Archive (zip)](https://download.jetbrains.com/idea/ideaIU-2017.3.7.win.zip)

[2017.3.7 - macOS (dmg)](https://download.jetbrains.com/idea/ideaIU-2017.3.7.dmg)

**IntelliJ IDEA Community Edition**

[2017.3.7 - Linux without JBR (tar.gz)](https://download.jetbrains.com/idea/ideaIC-2017.3.7-no-jdk.tar.gz)

[2017.3.7 - Linux x86_64 (tar.gz)](https://download.jetbrains.com/idea/ideaIC-2017.3.7.tar.gz)

[2017.3.7 - Sources Archive (zip)](https://github.com/JetBrains/intellij-community/archive/idea/173.4710.11.zip)

[2017.3.7 - Windows x64 (exe)](https://download.jetbrains.com/idea/ideaIC-2017.3.7.exe)

[2017.3.7 - Windows x64 ZIP Archive (zip)](https://download.jetbrains.com/idea/ideaIC-2017.3.7.win.zip)

[2017.3.7 - macOS (dmg)](https://download.jetbrains.com/idea/ideaIC-2017.3.7.dmg)


### Spring Tool Suite or Eclipse

STS version 3.9.1 is based on Eclipse Oxygen 1a (4.7.1a)

**[Spring Tool Suite (STS) 3.9.1](https://dist.springsource.com/release/STS/index.html)**

Windows [spring-tool-suite-3.9.1.RELEASE-e4.7.1a-win32.zip](https://dist.springsource.com/release/STS/3.9.1.RELEASE/dist/e4.7/spring-tool-suite-3.9.1.RELEASE-e4.7.1a-win32.zip)

Windows (64bit) [spring-tool-suite-3.9.1.RELEASE-e4.7.1a-win32-x86_64.zip](https://dist.springsource.com/release/STS/3.9.1.RELEASE/dist/e4.7/spring-tool-suite-3.9.1.RELEASE-e4.7.1a-win32-x86_64.zip)

Mac OS X (Cocoa, 64bit) [spring-tool-suite-3.9.1.RELEASE-e4.7.1a-macosx-cocoa-x86_64.dmg](https://dist.springsource.com/release/STS/3.9.1.RELEASE/dist/e4.7/spring-tool-suite-3.9.1.RELEASE-e4.7.1a-macosx-cocoa-x86_64.dmg)

Linux (GTK)  [spring-tool-suite-3.9.1.RELEASE-e4.7.1a-linux-gtk.tar.gz](https://dist.springsource.com/release/STS/3.9.1.RELEASE/dist/e4.7/spring-tool-suite-3.9.1.RELEASE-e4.7.1a-linux-gtk.tar.gz)

Linux (GTK, 64bit) [spring-tool-suite-3.9.1.RELEASE-e4.7.1a-linux-gtk-x86_64.tar.gz](https://dist.springsource.com/release/STS/3.9.1.RELEASE/dist/e4.7/spring-tool-suite-3.9.1.RELEASE-e4.7.1a-linux-gtk-x86_64.tar.gz)

**[Eclipse Oxygen 1a (4.7.1a)](https://www.eclipse.org/downloads/packages/release/oxygen/1a)**

Windows  [32-bit](https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/oxygen/1a/eclipse-jee-oxygen-1a-win32.zip)  [x86_64](https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/oxygen/1a/eclipse-jee-oxygen-1a-win32-x86_64.zip)

macOS  [x86_64](https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/oxygen/1a/eclipse-jee-oxygen-1a-macosx-cocoa-x86_64.dmg)

Linux  [x86_64](https://www.eclipse.org/downloads/download.php?file=/technology/epp/downloads/release/oxygen/1a/eclipse-jee-oxygen-1a-linux-gtk-x86_64.tar.gz)