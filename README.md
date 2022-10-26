# Spring Security Third Edition

Spring Security 3/e 스프링 시큐리티를 이용한 애플리케이션 보안

# Chapter 1 Anatomy of an Unsafe Application

## Security audit

Jim Bob Circle Pants Online Calendar (JBCPCalendar.com)


# Chapter 2 Getting Started with Spring Security

- 로그인 및 로그아웃 페이지 사용자 정의 customize both the login and logout
- 표현식 기반 접근 제한 expression-based access controls

## Hello Spring Security

### Importing the sample application

2장의 시작 코드인 chapter02.00-calendar 프로젝트를 IDE로 가져오자.

import the chapter02.00-calendar project into your IDE

각 장의 마일스톤 번호를 체크포인트로 해 코드를 수정해나갈 것

For each chapter, you will find multiple revisions of the code that represent checkpoints
within the book. 


### Updating your dependencies

> Gradle 전이적 의존성 (transitive dependencies)

### Using Spring 4.3 and Spring Security 4.2

스프링 IO Bill Of Materials (BOM) 의존성을 활용해 BOM이 가져온 모든 의존성 버전이 올바르게 작동하는지 확인

```
build.gradle
// Spring Security IO with ensures correct Springframework versions
dependencyManagement {
    imports {
        mavenBom 'io.spring.platform:platform-bom:${springIoVersion}'
    }
}
dependencies {
    ···
}
```

> STS를 사용하는 경우에는 build.gradle 파일을 업데이트할 때마다 
> Gradle > Refresh Gradle Project ... > OK를 선택해 모든 의존성을 업데이트하자.

Spring Security Reference [4.2.0.RELEASE](https://docs.spring.io/spring-security/site/docs/4.2.0.RELEASE/reference/htmlsingle) [4.2.x](https://docs.spring.io/spring-security/site/docs/4.2.x/reference/htmlsingle)

Spring Framework Reference [4.3.4.RELEASE](https://docs.spring.io/spring-framework/docs/4.3.4.RELEASE/spring-framework-reference/htmlsingle) [4.3.x](https://docs.spring.io/spring-framework/docs/4.3.x/spring-framework-reference/htmlsingle)

Spring Security Guides [4.2.3.RELEASE](https://docs.spring.io/spring-security/site/docs/4.2.3.RELEASE/guides/html5) [4.2.x](https://docs.spring.io/spring-security/site/docs/4.2.x/guides/html5)

### Implementing a Spring Security XML configuration file

> **자바 기반 설정 방식**에 대한 지원이 **스프링 3.1**의 스프링 프레임워크에 추가됐다.  
> **스프링 시큐리티 3.2** 릴리즈 이후부터 XML을 사용하지 않고도 스프링 시큐리티를 쉽게 구성할 수 있는 자바 기반 설정 방식을 지원한다.

스프링 시큐리티 [3.2.0.RC2](https://docs.spring.io/spring-security/site/docs/3.2.0.RC2/reference/htmlsingle)

### Updating your web.xml file

War Plugin [User Manual](https://docs.gradle.org/4.3/userguide/war_plugin.html) [DSL Reference](https://docs.gradle.org/4.3/dsl/org.gradle.api.tasks.bundling.War.html)

Eclipse WTP Plugin [User Manual](https://docs.gradle.org/4.3/userguide/eclipse_plugin.html) [DSL Reference](https://docs.gradle.org/4.3/dsl/org.gradle.plugins.ide.eclipse.model.EclipseWtpComponent.html)

**The ContextLoaderListener class**

Servlet 3.0+ initialization

Spring MVC provides the `o.s.w.WebApplicationInitializer` interface

`o.s.w.context.AbstractContextLoaderInitializer`

In Spring MVC, the preferred approach is to extend `o.s.w.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer`.

```
org.springframework.web.WebApplicationInitializer
├── org.springframework.web.context.AbstractContextLoaderInitializer
│   └── org.springframework.web.servlet.support.AbstractDispatcherServletInitializer
│       └── o.s.w.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
└── org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer
```

**ContextLoaderListener versus DispatcherServlet**

```
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SecurityConfig.class }; // Root beans of the parent ApplicationContext
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebMvcConfig.class }; // Child beans of the child ApplicationContext
	}
}
```

**The springSecurityFilterChain filter **

AbstractSecurityWebApplicationInitializer

**The DelegatingFilterProxy class**

web.xml

```
<filter>
    <filter-name>springSecurityFilterChain</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
<filter-mapping>
    <filter-name>springSecurityFilterChain</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

**The FilterChainProxy class**

security.xml

`org.springframework.security.web.FilterChainProxy`

Spring Security filters

> Due to the fact that both `DelegatingFilterProxy` and `FilterChainProxy` are the front door to Spring Security, when used in a web application, you would add a debug point when trying to figure out what is happening.

**Running a secured application**

[http://localhost:8080](http://localhost:8080)

> Your code should now look like `chapter02.01-calendar`.

### Customizing login

```
//src/main/java/com/packtpub/springsecurity/configuration/SecurityConfig.java
	http.authorizeRequests()
		···
		.formLogin()
			.loginPage("/login/form")
			.loginProcessingUrl("/login")
			.failureUrl("/login/form?error")
			.usernameParameter("username")
			.passwordParameter("password")
		···
```
```
//src/main/webapp/WEB-INF/templates/login.html

<div class="container">
	<!--/*/ <th:block th:include="fragments/header::header"></th:block> /*/-->
	<form th:action="@{/login}" method="POST" cssClass="form-horizontal">
		<div th:if="${param.error != null}" class="alert alert-danger">
			<strong>Failed to login.</strong>
			<span th:if="${session[SPRING_SECURITY_LAST_EXCEPTION] != null}">
				<span th:text="${session[SPRING_SECURITY_LAST_EXCEPTION].message}">
					Invalid credentials</span>
			</span>
		</div>
		<div th:if="${param.logout != null}" class="alert alert-success">
			You have been logged out.
		</div>
		<label for="username">Username</label>
		<input type="text" id="username" name="username" autofocus="autofocus" />
		<label for="password">Password</label>
		<input type="text" id="password" name="password" />
		<div class="form-actions">
			<input id="submit" class="btn" name="submit" type="submit" value="Login" />
		</div>
	</form>
</div>
```
```
//src/main/java/com/packtpub/springsecurity/web/configuration/WebMvcConfig.java

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
//@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurationSupport {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login/form")
			.setViewName("login");
	}

}
```

### Configuring logout

```
//src/main/java/com/packtpub/springsecurity/configuration/SecurityConfig.java

	http.authorizeRequests()
		···
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/login?logout")
		···
```
```
//src/main/webapp/WEB-INF/templates/fragments/header.html

<div id="navbar" ...>
	···
	<ul class="nav navbar-nav pull-right">
		<li><a id="navLogoutLink" th:href="@{/logout}">Logout</a></li>
	</ul>
	···
</div>
```

> Your code should now look like `chapter02.02-calendar`.


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


## A sample code structure

Each folder is named *chapterNN*, where *NN* is the chapter number.

Each *chapterNN* folder has additional folders containing each milestone project with the format *chapterNN.mm-calendar*, where *NN* is the chapter number and *mm* is the milestone within
that chapter.

For example, *chapter02.03-calendar* contains the milestone number *03* within *Chapter 2*, Getting Started with Spring Security, of the calendar application. The location of the preceding project would be *~/jbcpcalendar/chapter02/chapter02.03-calendar*.


## Importing the samples

Gradle IDE pack [(http://marketplace.eclipse.org/content/gradle-ide-pack)](http://marketplace.eclipse.org/content/gradle-ide-pack)

Buildship Gradle Integration plugin [(http://marketplace.eclipse.org/content/buildship-gradle-integration)](http://marketplace.eclipse.org/content/buildship-gradle-integration)

1. Go to **File** | **Import** and select **Existing Gradle Project**
2. Click on **Next**, as shown in the **Import Gradle Project**
3. Click on **Next**, as shown in the **Import Options**
4. You will see all of the projects listed in the **Import preview**
5. Click on **Finish**.


## Running the samples

### Starting the samples within IDEA

1. Go to **File** | **Run** and select **Edit Configurations...**
2. You will be presented with options to **Add New Configuration**s. Select the plus (+) sign in the upper-left corner to choose a new **Gradle** configuration
3. Now, you can give it a name like *chapter15.00 (bootRun)* and select the
actual milestone directory for this configuration. Finally, enter *bootRun* under
the **Tasks** option to execute
4. Select the configuration you want to execute; click on the green **run** button or use the *Shift + F10* key

## Gradle tasks

In *Chapter 1, Anatomy of an Unsafe Application*, and *Chapter 2, Getting Started with Spring Security*, the **Gradle task** to run the project will be **tomcatRun**. For the rest of the chapters in the book, Spring Boot has been used, and the **Gradle task** to start the project will be **bootRun**.

### Starting the samples within STS

In STS, a **Run Configurations** is also created and the same information needs to be included
for each milestone project to run

