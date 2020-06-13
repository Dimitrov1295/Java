package uk.co.ivandimitrov.EQList.data.features.geometry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinate {
    private Number loc;

    public Coordinate(double loc) {
        this.loc = loc;
    }

    public Coordinate(int loc) {
        this.loc = loc;
    }
}