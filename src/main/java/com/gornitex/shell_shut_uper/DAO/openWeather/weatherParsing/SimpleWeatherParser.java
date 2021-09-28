package com.gornitex.shell_shut_uper.DAO.openWeather.weatherParsing;

import com.github.prominence.openweathermap.api.model.forecast.Forecast;
import com.github.prominence.openweathermap.api.model.forecast.WeatherForecast;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.gornitex.shell_shut_uper.models.forecast.ParsedWeather;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@Component
public class SimpleWeatherParser implements WeatherParser<ParsedWeather> {
	private final static String celsius = "°C";
	private final static DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd-MM");
	private final static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

	@Override
	public ParsedWeather parseWeather(Weather weather) {
		LocalDateTime weatherLocalDateTime = weather.getCalculationTime();
		String date = weatherLocalDateTime.format(dayFormatter);
		String time = weatherLocalDateTime.format(timeFormatter);
		String dayTime = weatherLocalDateTime.getHour() > 15 || weatherLocalDateTime.getHour() < 7 ? "NIGHT" : "DAY";
		String temperature = Math.round(weather.getTemperature().getValue()) + celsius;
		String feelsLike = Math.round(weather.getTemperature().getFeelsLike()) + celsius;
		String weatherState = weather.getWeatherState().getDescription();
		return new ParsedWeather(date, time, dayTime, temperature, feelsLike, weatherState);
	}

	@Override
	public List<ParsedWeather> parseWeather(Forecast forecast) {
		List<ParsedWeather> parsedWeatherList = new LinkedList<>();
		String date, time, dayTime, temperature, feelsLike, weatherState;
		for (WeatherForecast weatherForecast : forecast.getWeatherForecasts()) {
			LocalDateTime weatherLocalDateTime = weatherForecast.getForecastTime();
			date = weatherLocalDateTime.format(dayFormatter);
			time = weatherLocalDateTime.format(timeFormatter);
			dayTime = weatherForecast.getDayTime().toString();
			temperature = Math.round(weatherForecast.getTemperature().getValue()) + celsius;
			feelsLike = Math.round(weatherForecast.getTemperature().getFeelsLike()) + celsius;
			weatherState = weatherForecast.getWeatherState().getDescription();
			parsedWeatherList.add(new ParsedWeather(date, time, dayTime, temperature, feelsLike, weatherState));
		}
		return parsedWeatherList;
	}
}
