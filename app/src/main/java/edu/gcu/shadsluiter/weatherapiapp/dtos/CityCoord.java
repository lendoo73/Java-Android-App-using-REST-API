package edu.gcu.shadsluiter.weatherapiapp.dtos;

import org.json.JSONException;
import org.json.JSONObject;

public class CityCoord {

    private float lat;
    private float lon;
    private String name;
    private String label;
    private String continent;
    private String countryCode;

    public CityCoord() {}

    public CityCoord(JSONObject cityInfo) {
        try {
            lat = cityInfo.getLong("latitude");
            lon = cityInfo.getLong("longitude");
            name = cityInfo.getString("name");
            label = cityInfo.getString("label");
            continent = cityInfo.getString("continent");
            countryCode = cityInfo.getString("country_code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public String getName() {
        return name;
    }

    public String getLabel() {
        return label;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return String.format(
                "%s\n" +
                "%s\n" +
                "%s (%s)\n" +
                "lat: %.4f\tlon: %.4f",
                continent,
                name,
                label, countryCode,
                lat, lon
        );
    }
}
