package edu.gcu.shadsluiter.weatherapiapp.dtos;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather {

    private int id;
    private String main;
    private String description;
    private String icon;

    public Weather(JSONObject weather) {
        try {
            id = weather.getInt("id");
            main = weather.getString("main");
            description = weather.getString("description");
            icon = weather.getString("icon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
