package uk.co.ivandimitrov.EQList.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import uk.co.ivandimitrov.EQList.data.features.properties.Feature;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Earthquake {
    @JsonProperty("features")
    private List<Feature> features;

}


