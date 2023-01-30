[jitpack]: https://img.shields.io/jitpack/v/github/Kopnu/spring-boot-starter-jda
# Spring Boot Starter for JDA (Java Discord API)

This is a simple Spring Boot starter for JDA project. See [JDA](https://github.com/DV8FromTheWorld/JDA).
Required for configuration JDA as a bean.

## Summary

1. [Usage](#Usage)
2. [Properties](#Properties)

## Usage

**Setup your Spring Boot application first**

**Add repository**

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

**Add dependency**

Check latest release version here: [GitHub Releases](https://github.com/Kopnu/spring-boot-starter-jda/releases)

[ ![jitpack][] ](https://jitpack.io/#Kopnu/spring-boot-starter-jda)

```xml
<dependency>
    <groupId>com.github.Kopnu</groupId>
    <artifactId>spring-boot-starter-jda</artifactId>
    <version>VERSION</version>
</dependency>
```

**Create Main class using Spring Boot**
```java
@SpringBootApplication
public class Bot {

    public static void main(String[] args) {
        SpringApplication.run(Bot.class, args);
    }

}
```
**Setup `application.yaml`**

You should to paste your bot token into `spring.jda.token` property.

```yaml
spring:
  jda:
    token: PAST_YOUR_TOKEN
```

**Create `EventListener` for your bot**

You can use `@JdaEventListener` instead of `@Component`. It will be more functionality in the future.

```java
@JdaEventListener
public class JdaReadyEventListener implements EventListener {

    @Override
    public void onEvent(@NotNull GenericEvent genericEvent) {
        if (genericEvent instanceof ReadyEvent readyEvent) {
            System.out.println("Bot is ready!");
        }
    }

}
```

That's all you need.

If you want to add your configuration to JDA. For example, you can add intents: 

```java
@Configuration
public class JdaConfig {

    @Bean
    public JDA jda(JDABuilder jdaBuilder) {
        // Configure your JDA here
        jdaBuilder.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));
        return jdaBuilder.build();
    }

}
```

**Usage/Migrate example** 

You can see example here: https://github.com/Kopnu/study-discord-bot/pull/1/files

## Properties

|Property|Description|Example|
|----------------------------|-----------------------|-----------------------|
|spring.jda.autoCreate       | Enable auto configure JDA<br/>true/false<br/>true by default|false - if you want to disable this starter|
|spring.jda.token            | Bot token | |
|spring.jda.activity.type    | Activity type for your bot<br/>* DEFAULT<br/>* STREAMING<br/>* LISTENING<br/>* COMPETING|listening|
|spring.jda.activity.activity| Activity description |Courses about self-development|

