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
$ .gradlew idea
```
```
C:\jbcpcalendar> gradlew.bat idea
```
IntelliJ project files are created with the idea task, and STS or any Eclipse-based IDE’s project files are created with the eclipse task.

[IntelliJ Plugin](https://docs.gradle.org/current/userguide/idea_plugin.html)

[Eclipse Plugin](https://docs.gradle.org/current/userguide/eclipse_plugin.html)
