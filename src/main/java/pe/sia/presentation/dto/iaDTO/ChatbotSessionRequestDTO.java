package pe.sia.presentation.dto.iaDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChatbotSessionRequestDTO {
    private String chatbotid;
    private String name;
    private String email;
}
