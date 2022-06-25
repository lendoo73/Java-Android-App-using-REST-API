package edu.gcu.shadsluiter.weatherapiapp.dtos;

import org.json.JSONException;
import org.json.JSONObject;

public class MainWeather {

    private float temp;
    private float feelsLike;
    private float tempMin;
    private float tempMax;
    private float pressure;
    private float humidity;

    public MainWeather(JSONObject main) {
        try {
            temp = main.getLong("temp");
            feelsLike = main.getLong("feels_like");
            tempMin = main.getLong("temp_min");
            tempMax = main.getLong("temp_max");
            pressure = main.getLong("pressure");
            humidity = main.getLong("humidity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Float getTemp() {
        return temp;
    }

    public Float getFeelsLike() {
        return feelsLike;
    }

    public Float getTempMin() {
        return tempMin;
    }

    public Float getTempMax() {
        return tempMax;
    }

    public Float getPressure() {
        return pressure;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public void setFeelsLike(Float feelsLike) {
        this.feelsLike = feelsLike;
    }

    public void setTempMin(Float tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(Float tempMax) {
        this.tempMax = tempMax;
    }

    public void setPressure(Float pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "MainWeather{" +
                "temp=" + temp +
                ", feelsLike=" + feelsLike +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
