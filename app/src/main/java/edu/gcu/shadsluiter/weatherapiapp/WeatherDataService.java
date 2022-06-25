package edu.gcu.shadsluiter.weatherapiapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.gcu.shadsluiter.weatherapiapp.dtos.ApiKeys;
import edu.gcu.shadsluiter.weatherapiapp.dtos.CityCoord;
import edu.gcu.shadsluiter.weatherapiapp.models.WeatherReportModel;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_COORD =
            "http://api.positionstack.com/v1/forward?query=%s&access_key=%s";
    public static final String QUERY_FOR_CITY_WEATHER_BY_NAME =
            "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    public static final String QUERY_FOR_CITY_WEATHER_BY_COORD =
            "https://api.openweathermap.org/data/2.5/weather?lat=%.5f&lon=%.5f&appid=%s";

    private Context context;
    private String errorMessage;
    private List<CityCoord> cities;

    public WeatherDataService(Context context) {
        this.context = context;
        cities = new ArrayList<>();
    }

    public interface VolleyResponseListener {
        void onError(String messsage);

        void onResponse(List<CityCoord> citiesResponse);
    }

    public void getCityCoord(String cityName, VolleyResponseListener volleyResponseListener) {
        // https://stackoverflow.com/questions/45940861/android-8-cleartext-http-traffic-not-permitted
        String url = String.format(QUERY_FOR_CITY_COORD,
                cityName,
                ApiKeys.POSITIONSTACK_ACCESS_KEY
        );
        // https://google.github.io/volley/request.html
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                CityCoord cityCoord = null;
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject cityInfo = jsonArray.getJSONObject(i);
                        cities.add(new CityCoord(cityInfo));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                volleyResponseListener.onResponse(cities);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorMessage = "ERROR: " + error.toString();
                volleyResponseListener.onError(errorMessage);
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public interface ForecastByCoordResponse {
        void onError(String messsage);

        void onResponse(WeatherReportModel weatherReportModel);
    }

    public void getCityForecastByCoord(CityCoord cityInfo, ForecastByCoordResponse forecastByCoordResponse) {
        String url = String.format(QUERY_FOR_CITY_WEATHER_BY_COORD,
                cityInfo.getLat(),
                cityInfo.getLon(),
                ApiKeys.OPENWEATHER_API_KEY);
        if (cityInfo.getLat() == 0 && cityInfo.getLon() == 0) {
            url = String.format(QUERY_FOR_CITY_WEATHER_BY_NAME,
                    cityInfo.getName().replaceAll(" ","%20"),
                    ApiKeys.OPENWEATHER_API_KEY
            );
        }
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                forecastByCoordResponse.onResponse(new WeatherReportModel(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                errorMessage = "ERROR: " + error.toString();
                //forecastByCoordResponse.onError(errorMessage);
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public interface GetCityForecastByNameCallback {
        void onError(String message);
        void onResponse(WeatherReportModel weatherReportModel);
    }

    public interface GetCityForecastByNameCallbackList {
        void onError(String message);
        void onResponse(List<WeatherReportModel> weatherReportModel);
    }

    public void getCityForecastByName(String cityName, GetCityForecastByNameCallbackList getCityForecastByNameCallbackList) {
        getCityCoord(cityName, new VolleyResponseListener() {
            @Override
            public void onError(String messsage) {
                Toast.makeText(context, messsage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<CityCoord> cities) {
                List<WeatherReportModel> reports = new ArrayList<>();

                for (int i = 0; i < cities.size(); i++) {
                    getCityForecastByCoord(cities.get(i), new ForecastByCoordResponse() {
                        @Override
                        public void onError(String messsage) {
                            Toast.makeText(context, messsage, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(WeatherReportModel weaterReportModel) {
                            reports.add(weaterReportModel);
                            if (reports.size() > 1) {
                                getCityForecastByNameCallbackList.onResponse(reports);
                            }
                        }
                    });
                }
            }
        });
    }

}
