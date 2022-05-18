package per.tom.clientview.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:/config/desiConfig.properties")
public class DesiConfig {

    @Value("${desi.name.max}")
    private Integer maxDesiName;

    @Value("${desi.name.min}")
    private Integer minDesiName;

}
