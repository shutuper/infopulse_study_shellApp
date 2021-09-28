package com.gornitex.shell_shut_uper.services.openWeather;

import com.gornitex.shell_shut_uper.DAO.openWeather.OpenWeatherReceiver;
import com.gornitex.shell_shut_uper.models.forecast.ParsedWeather;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OpenWeatherService<T> {

	private final OpenWeatherReceiver<T> openWeatherReceiver;
	private final ParsedWeather weatherTableHeaders;

	public List<T> getForecast(String cityName) {
		List<T> parsedWeatherList = new ArrayList<>(getParsedWeatherListWithHeaders());
		parsedWeatherList.add(openWeatherReceiver.getTodayWeather(cityName));
		return parsedWeatherList;
	}

	public List<T> getForecast(int days, String cityName) {
		List<T> parsedWeatherList = new ArrayList<>(getParsedWeatherListWithHeaders());
		parsedWeatherList.addAll(openWeatherReceiver.get5Day3HourStepForecast(days, cityName));
		return parsedWeatherList;
	}

	private List<T> getParsedWeatherListWithHeaders() {
		return (List<T>) List.of(weatherTableHeaders);
	}
}
