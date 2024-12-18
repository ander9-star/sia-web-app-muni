package pe.sia.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoPeticion {
    private int statusCode;
    private String error; 
    private String message; 
    
} 
