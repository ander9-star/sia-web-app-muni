package pe.sia.presentation.controller.iaController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pe.sia.configuration.app.ChatbotConfig;
import pe.sia.presentation.dto.iaDTO.ChatbotSessionRequestDTO;
import pe.sia.presentation.dto.iaDTO.ChatbotSessionResponseDTO;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/adminuser")
public class ChatbotIAController {

    //@Value("${chatbot.api.key}")
    private final String apiKey;

    public ChatbotIAController(ChatbotConfig chatbotConfig) {
        this.apiKey = chatbotConfig.getApi().getKey();
    }

    @PostMapping("/api/chatbot/session")
    public ResponseEntity<?> createChatbotSesion() {
        try {
            // si la api esta vacia
            if(apiKey == null || apiKey.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("La API Key no esta configurada");
            }
            // estebleciendo las cabezeras de autorizacion
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);

            // estableciendo la peticion del cuerpo
            ChatbotSessionRequestDTO requestBody = new ChatbotSessionRequestDTO(
                "bb385aff33f4d00ee66e8e958233c857",
                "chatbot-sia",
                "jvillaltaca12@ucvvirtual.edu.pe"
            );

            HttpEntity<ChatbotSessionRequestDTO> requestEntity = new HttpEntity<>(requestBody, headers);

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<ChatbotSessionResponseDTO> response = restTemplate.postForEntity(
                "https://www.askyourdatabase.com/api/chatbot/v2/session",
                requestEntity,
                ChatbotSessionResponseDTO.class
            );

            if(response.getStatusCode() != HttpStatus.OK) {
                return ResponseEntity.status(response.getStatusCode())
                                     .body("Error en la respuesta del servidor");
            }

            return ResponseEntity.ok(response.getBody());

        } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
