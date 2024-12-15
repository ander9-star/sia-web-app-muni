package pe.sia.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UtilsApp {

    private static final String FORMATO_FECHA = "yyyy-MM-dd HH:mm:ss";

    private UtilsApp() {

    }

    public static String getFormatoFechaActual() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        return dateTimeFormatter.format(dateTime);
    }

    public static String formatearFecha(Instant dateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMATO_FECHA)
                                                                .withZone(ZoneId.systemDefault()); 
        return dateTimeFormatter.format(dateTime);
    }

    public static Instant formatearFechaInstant(String fecha) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMATO_FECHA, Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse(fecha, dateTimeFormatter);
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

}
