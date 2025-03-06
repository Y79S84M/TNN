package fr.ysaintmartin.tnn.utility;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class CustomDateTimeFormatter {

    public String formatDateTime(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss"));
    }
}
