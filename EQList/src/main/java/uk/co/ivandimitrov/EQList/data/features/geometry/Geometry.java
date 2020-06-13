package uk.co.ivandimitrov.EQList.data.features.geometry;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    @JsonProperty("coordinates")
    Coordinate[] coordinates;

    /**
     * @return returns the value to be appended to the Google Maps search URL so
     *         that it displays the origin of the earthquake.
     */
    public String getMapsUrl() {
        double lon = coordinates[0].getLoc().doubleValue();
        double lat = coordinates[1].getLoc().doubleValue();
        return GeoUtil.convertDdToDMS(lat, lon) + "/@" + lat + "," + lon + "," + "5z";
    }
}

class GeoUtil {

    // private helper method that takes a lat or lon and converts it to DMS.
    private static String toDMS(double coordinate) {
        double absolute = Math.abs(coordinate);
        int degrees = (int) absolute;
        double minutesNotTruncated = (absolute - degrees) * 60;
        int minutes = (int) minutesNotTruncated;
        int seconds = (int) ((minutesNotTruncated - minutes) * 60);
        return degrees + "°" + minutes + "'" + seconds + '"';
    }

    /**
     * Converts Decimal Degrees to Degrees, Minutes and Seconds. This method is
     * needed for the generation of Google Maps URLs.
     * 
     * @param lat The latitude you want to convert to DMS.
     * @param lon The longitude you want to convert to DMS.
     * @return returns a String representing the provided lat and lon values in
     *         Degrees, Minutes and Seconds
     */
    public static String convertDdToDMS(double lat, double lon) {
        String latitude = toDMS(lat);
        String longitude = toDMS(lon);
        return latitude + (lat >= 0 ? "N" : "S") + " " + longitude + (lon >= 0 ? "E" : "W");
    }
}