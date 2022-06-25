package edu.gcu.shadsluiter.weatherapiapp.dtos;

import org.json.JSONException;
import org.json.JSONObject;

public class Wind {

    private float speed;
    private Integer deg;
    private float gust;

    public Wind(JSONObject wind) {
        try {
            speed = wind.getLong("speed");
            deg = wind.getInt("deg");
            gust = wind.optLong("gust");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Float getSpeed() {
        return speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public Float getGust() {
        return gust;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public void setGust(Float gust) {
        this.gust = gust;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                ", gust=" + gust +
                '}';
    }
}
