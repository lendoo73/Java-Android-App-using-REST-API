package edu.gcu.shadsluiter.weatherapiapp.dtos;

import org.json.JSONException;
import org.json.JSONObject;

public class Sys {

    private Integer type;
    private Integer id;
    private String countryCode;
    private Long sunrise;
    private Long sunset;

    public Sys(JSONObject sys) {
        try {
            type = sys.optInt("type");
            id = sys.optInt("id");
            countryCode = sys.getString("country");
            sunrise = sys.getLong("sunrise");
            sunset = sys.getLong("sunset");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Integer getType() {
        return type;
    }

    public Integer getId() {
        return id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "type=" + type +
                ", id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }
}
