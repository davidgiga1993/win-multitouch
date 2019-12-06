# About

This library provides an interface for listening for multitouch inputs on windows using java.
It treats mouse, pen and finger the same way. 


## Release
The build dll and jar binary is available at the maven repo:

```xml
<repository>
    <id>dev-core-public</id>
    <name>Public dev-core repo</name>
    <url>https://dev-core.org/maven-repo-pub</url>
</repository>

<dependency>
    <groupId>org.devcore</groupId>
    <artifactId>win-multitouch</artifactId>
    <version>1.0</version>
</dependency>
``` 

```groovy
allprojects {
    repositories {
        mavenLocal()
        jcenter()
        maven {
            name "dev-core-public"
            url "https://dev-core.org/maven-repo-pub/"
        }
    }
}

dependencies {
    implementation 'org.devcore:win-multitouch:1.0'
}
```
