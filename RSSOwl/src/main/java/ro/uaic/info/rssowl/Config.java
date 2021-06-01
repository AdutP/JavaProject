package ro.uaic.info.rssowl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {
    public final String token;
    public final Map<String, String> rssSources;

    public Config(
            @JsonProperty("token") String token,
            @JsonProperty("rssSources") Map<String, String> rssSources
    ) {
        this.token = token;
        this.rssSources = rssSources;
    }

}
