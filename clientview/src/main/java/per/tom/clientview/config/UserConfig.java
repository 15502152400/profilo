package per.tom.clientview.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource("classpath:/config/userConfig.properties")
@Getter
public class UserConfig {

    @Value("${user.name.max}")
    private Integer maxUserName;

    @Value("${user.name.min}")
    private Integer minUserName;

}
