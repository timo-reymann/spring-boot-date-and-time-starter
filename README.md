spring-boot-date-and-time-starter
===
[![Maven Central Version](https://maven-badges.herokuapp.com/maven-central/com.github.timo-reymann/spring-boot-date-and-time-starter/badge.svg)](https://search.maven.org/search?q=g:com.github.timo-reymann%20AND%20a:spring-boot-date-and-time-starter&core=gav)

## What is this?
Customizable output format for LocalDate and LocalDateTime in Spring Boot using Jackson Object Mapper builtin to spring-boot-starter-web

## Requirements
Spring Boot 2.2.x+ 

### How to use?
Simply add it to your dependencies
```xml
<dependency>
    <groupId>com.github.timo-reymann</groupId>
    <artifactId>spring-boot-date-and-time-starter</artifactId>
    <version>2.2.1</version>
</dependency>
```

To customize the output format use your application yml:

```yaml
jackson.date-and-time:
  date-format: 'dd.MM.YYYY'
  date-time-format: 'dd.MM.YYYY HH:mm:ss'
```

The default values are ....

... for LocalDate: `yyyy-MM-dd`

... for LocalDateTime: `yyyy-MM-dd HH:mm:ss`
