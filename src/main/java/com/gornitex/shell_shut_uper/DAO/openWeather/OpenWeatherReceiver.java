package com.gornitex.shell_shut_uper.DAO.openWeather;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.forecast.Forecast;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.gornitex.shell_shut_uper.DAO.openWeather.weatherParsing.WeatherParser;
import com.gornitex.shell_shut_uper.models.forecast.ParsedWeather;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@AllArgsConstructor
public class OpenWeatherReceiver<T> {

	private final OpenWeatherMapClient openWeatherMapClient;
	private final WeatherParser<T> weatherParser;

	public T getTodayWeather(String cityName) {
		Weather weather = openWeatherMapClient
				.currentWeather()
				.single()
				.byCityName(cityName)
				.language(Language.ENGLISH)
				.unitSystem(UnitSystem.METRIC)
				.retrieve()
				.asJava();
		return weatherParser.parseWeather(weather);
	}

	public List<T> get5Day3HourStepForecast(int days, String cityName) {
		if (days < 1 || days > 5) throw new IllegalArgumentException("days must be >0 and 5<");
		Forecast forecast = openWeatherMapClient
				.forecast5Day3HourStep()
				.byCityName(cityName)
				.language(Language.ENGLISH)
				.unitSystem(UnitSystem.METRIC)
				.count(days * 8)
				.retrieve()
				.asJava();
		return weatherParser.parseWeather(forecast);
	}

}
