package edu.gcu.shadsluiter.weatherapiapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.gcu.shadsluiter.weatherapiapp.dtos.CityCoord;
import edu.gcu.shadsluiter.weatherapiapp.dtos.Clouds;
import edu.gcu.shadsluiter.weatherapiapp.dtos.MainWeather;
import edu.gcu.shadsluiter.weatherapiapp.dtos.Sys;
import edu.gcu.shadsluiter.weatherapiapp.dtos.Weather;
import edu.gcu.shadsluiter.weatherapiapp.dtos.Wind;

public class WeatherReportModel {

    private CityCoord cityCoord;
    private List<Weather> weatherList;
    private String base;
    private MainWeather mainWeather;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Long now;
    private Sys sys;
    private Integer timezone;
    private Integer id;
    private String name;
    private Integer cod;

    public WeatherReportModel() {
        weatherList = new ArrayList<>();
    }

    public WeatherReportModel(JSONObject response) {
        weatherList = new ArrayList<>();
        try {
            JSONArray weathers = response.getJSONArray("weather");
            for (int i = 0; i < weathers.length(); i++) {
                weatherList.add(new Weather(weathers.getJSONObject(i)));
            }

            base = response.getString("base");
            mainWeather = new MainWeather(response.getJSONObject("main"));
            visibility = response.getInt("visibility");
            wind = new Wind(response.getJSONObject("wind"));
            clouds = new Clouds(response.getJSONObject("clouds"));
            now = response.getLong("dt");
            sys = new Sys(response.getJSONObject("sys"));
            timezone = response.getInt("timezone");
            id = response.getInt("id");
            name = response.getString("name");
            cityCoord = new CityCoord();
            cityCoord.setLat(response.getJSONObject("coord").getLong("lat"));
            cityCoord.setLon(response.getJSONObject("coord").getLong("lon"));
            cityCoord.setName(name);
            cityCoord.setLabel(String.format("%s, $s", name, sys.getCountryCode()));
            cod = response.getInt("cod");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public WeatherReportModel(CityCoord cityCoord, List<Weather> weatherList, String base, MainWeather mainWeather,
                              Integer visibility, Wind wind, Clouds clouds, Long now, Sys sys, Integer timezone,
                              Integer id, String name, Integer cod) {
        this.cityCoord = cityCoord;
        this.weatherList = weatherList;
        this.base = base;
        this.mainWeather = mainWeather;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.now = now;
        this.sys = sys;
        this.timezone = timezone;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public CityCoord getCityCoord() {
        return cityCoord;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public String getBase() {
        return base;
    }

    public MainWeather getMainWeather() {
        return mainWeather;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public Long getNow() {
        return now;
    }

    public Sys getSys() {
        return sys;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCityCoord(CityCoord cityCoord) {
        this.cityCoord = cityCoord;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setMainWeather(MainWeather mainWeather) {
        this.mainWeather = mainWeather;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public void setVisibility(JSONObject visibility) {
        this.visibility = Integer.valueOf(visibility.toString());
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public void setNow(Long now) {
        this.now = now;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public void addWeather(Weather weather) {
        weatherList.add(weather);
    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s (%s)\n"
                        + "Lat: %.4f\t Lon: %.4f\n"
                        + "%s\n"
                        + "Temperature: %.1f\n"
                        + "Wind speed: %.2f\n",
                name, sys.getCountryCode(),
                cityCoord.getLat(), cityCoord.getLon(),
                weatherList.get(0).getDescription(),
                mainWeather.getTemp(),
                wind.getSpeed()
        );
    }
}
