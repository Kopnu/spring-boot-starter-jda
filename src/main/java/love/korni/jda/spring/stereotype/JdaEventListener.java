package love.korni.jda.spring.stereotype;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that an annotated class is a "EventListener" from JDA.
 * This annotation also serves as a specialization of @Component, allowing for implementation classes to be autodetected
 * through classpath scanning.
 *
 * @author Sergei_Konilov
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface JdaEventListener {

    @AliasFor(annotation = Component.class)
    String value() default "";

}
