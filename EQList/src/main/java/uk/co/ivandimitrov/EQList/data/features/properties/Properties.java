package uk.co.ivandimitrov.EQList.data.features.properties;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
    private double mag;
    private String place;
    private long time;

    public String getDateTime() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(this.getTime()), ZoneId.systemDefault())
                .format(java.time.format.DateTimeFormatter.ofPattern("dd/ MMMM / YYYY, hh:mm:ss")).toString();
    }
}