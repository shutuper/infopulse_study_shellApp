package com.gornitex.shell_shut_uper.DAO.openWeather.weatherParsing;

import com.github.prominence.openweathermap.api.model.forecast.Forecast;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.springframework.stereotype.Component;

import java.util.List;


public interface WeatherParser<T> {

	T parseWeather(Weather weather);

	List<T> parseWeather(Forecast forecast);

}
