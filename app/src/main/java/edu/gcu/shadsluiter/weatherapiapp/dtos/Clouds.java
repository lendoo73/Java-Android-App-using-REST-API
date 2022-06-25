package edu.gcu.shadsluiter.weatherapiapp.dtos;

import org.json.JSONException;
import org.json.JSONObject;

public class Clouds {
    private Integer all;

    public Clouds(JSONObject clouds) {
        try {
            all = clouds.getInt("all");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
