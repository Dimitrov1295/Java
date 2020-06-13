package uk.co.ivandimitrov.EQList.data.features.properties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import uk.co.ivandimitrov.EQList.data.features.geometry.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Feature {
    @JsonProperty("properties")
    private Properties properties;
    @JsonProperty("geometry")
    private Geometry geometry;
}