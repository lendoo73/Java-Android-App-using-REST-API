package edu.gcu.shadsluiter.weatherapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gcu.shadsluiter.weatherapiapp.dtos.CityCoord;
import edu.gcu.shadsluiter.weatherapiapp.models.WeatherReportModel;

public class MainActivity extends AppCompatActivity {

    private Button btn_cityID, btn_getWeatherByID, btn_getWeatherByName;
    private EditText et_dataInput;
    private ListView lv_weatherReport;
    private final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign values to each control on the layout.
        btn_cityID = findViewById(R.id.btn_getCityID);
        btn_getWeatherByID = findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByName = findViewById(R.id.btn_getWeatherByCityName);

        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReport = findViewById(R.id.lv_weatherReports);

        // click listeners for each button.
        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityCoord cityCoord = new CityCoord();
                cityCoord.setName(et_dataInput.getText().toString());
                weatherDataService.getCityForecastByCoord(cityCoord, new WeatherDataService.ForecastByCoordResponse() {
                    @Override
                    public void onError(String messsage) {
                        Toast.makeText(MainActivity.this, "Error: " + messsage.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(WeatherReportModel weather) {
//                        Toast.makeText(MainActivity.this, weaterReportModel.toString(), Toast.LENGTH_SHORT).show();
                        List<WeatherReportModel> responses = new ArrayList<>();
                        responses.add(weather);
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, responses);
                        lv_weatherReport.setAdapter(arrayAdapter);
                    }
                });
            }
        });

        btn_getWeatherByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherDataService.getCityForecastByName(
                        et_dataInput.getText().toString(),
                        new WeatherDataService.GetCityForecastByNameCallbackList() {
                    @Override
                    public void onError(String messsage) {
                        Toast.makeText(MainActivity.this, "Error: " + messsage.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weathers) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, weathers);
                        lv_weatherReport.setAdapter(arrayAdapter);
                    }
                });
            }
        });

        btn_cityID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                weatherDataService.getCityCoord(et_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String messsage) {
                        Toast.makeText(MainActivity.this, messsage, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<CityCoord> cities) {
                        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, cities);
                        lv_weatherReport.setAdapter(arrayAdapter);
                    }
                });
            }
        });

    }
}