package pe.sia.configuration.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "chatbot")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChatbotConfig {
    
    private API api;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class API {
        private String key;
    }
    
}