spring-boot-date-and-time-starter
===

## What is this?
Customizable output format for LocalDate and LocalDateTime in Spring Boot using Jackson Object Mapper builtin to spring-boot-starter-web

## Requirements
If you use Spring Boot 1.5.x the latest supported version is 1.0.0,
if you use Spring Boot 2.x you must use 2.0.0+

### How to use?
Simply add it to your dependencies
```xml
<dependency>
    <groupId>com.github.timo-reymann</groupId>
    <artifactId>spring-boot-date-and-time-starter</artifactId>
    <version>2.0.0</version>
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