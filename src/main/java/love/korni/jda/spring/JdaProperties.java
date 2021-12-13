package love.korni.jda.spring;

import lombok.Data;
import net.dv8tion.jda.api.entities.Activity.ActivityType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Properties for autoconfiguration JDA.
 *
 * @author Sergei_Konilov
 */
@Data
@ConfigurationProperties(prefix = "spring.jda")
public class JdaProperties {

    private Boolean autoCreate;

    private String token;

    @NestedConfigurationProperty
    private Activity activity;

    @Data
    public static class Activity {

        private ActivityType type = ActivityType.DEFAULT;

        private String activity;

    }

}
