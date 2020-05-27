package uk.co.ivandimitrov.EQList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This object stores earthquake data like name, location, magnitude and so on.
 */
public class Earthquake {
    private String title;
    private double mag;
    private String location;
    private LocalDateTime time;
    private String dms;
    private double lat;
    private double lon;

    public String getTitle() {
        return this.title;
    }

    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("dd/ MMMM / YYYY, hh:mm:ss")).toString();
    }

    public double getMag() {
        return this.mag;
    }

    public String getLocation() {
        return this.location;
    }

    public String getDms() {
        return this.dms;
    }

    /**
     * 
     * @return returns the value to be appended to the Google Maps search URL so
     *         that it displays the origin of the earthquake.
     */
    public String getMapsUrl() {
        return this.getDms() + "/@" + this.lat + "," + this.lon + "," + "5z";
    }

    public Earthquake(String title, double mag, String location, LocalDateTime time, String dms, double lat,
            double lon) {
        this.title = title;
        this.mag = mag;
        this.location = location;
        this.time = time;
        this.dms = dms;
        this.lat = lat;
        this.lon = lon;
    }

}