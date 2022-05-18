package per.tom.clientview.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/config/profiloConfig.properties")
@Getter
public class ProfiloConfig {

    @Value("${profilo.name.ch.max}")
    private Integer profiloChNameMaxSize;

    @Value("${profilo.name.ch.min}")
    private Integer profiloChNameMinSize;

    @Value("${profilo.name.en.max}")
    private Integer profiloEnNameMaxSize;

    @Value("${profilo.name.en.min}")
    private Integer profiloEnNameMinSize;

    @Value("${profilo.coverImg.height}")
    private Integer profiloCoverImgHeight;

    @Value("${profilo.coverImg.width}")
    private Integer profiloCoverImgWidth;

    @Value("${profilo.coverImg.size.max}")
    private Long profiloCoverSizeMax;

    @Value("${profilo.coverImg.size.min}")
    private Long profiloCoverSizeMin;

    @Value("${profilo.coverImg.type}")
    private String[] coverImgType;

}
