package com.gornitex.shell_shut_uper.controllers.shell;

import com.gornitex.shell_shut_uper.models.forecast.ParsedWeather;
import com.gornitex.shell_shut_uper.services.openWeather.OpenWeatherService;
import com.gornitex.shell_shut_uper.services.openWeather.WeatherTableBuilder;
import com.gornitex.shell_shut_uper.services.shellValueProviders.CityNamesValueProvider;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class ForecastShell {

	private final OpenWeatherService<ParsedWeather> openWeatherService;
	private final WeatherTableBuilder<ParsedWeather> weatherTableBuilder;

	@ShellMethod(value = "Get current day weather in entered city.", key = {"weather", "w", "tw"})
	public Table weather(@ShellOption(valueProvider = CityNamesValueProvider.class) String cityName) {
		return weatherTableBuilder.buildTable(openWeatherService.getForecast(cityName));
	}

	@ShellMethod(value = "Get (1-5) days forecast .", key = {"forecast", "f", "fcst"})
	public Table weather(int days, @ShellOption(valueProvider = CityNamesValueProvider.class) String cityName) {
		System.out.println("<==============================" + cityName + "==============================>");
		return weatherTableBuilder.buildTable(openWeatherService.getForecast(days, cityName));
	}


}
