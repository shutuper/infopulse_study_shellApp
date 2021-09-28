package com.gornitex.shell_shut_uper.controllers.rest;

import com.gornitex.shell_shut_uper.models.forecast.ParsedWeather;
import com.gornitex.shell_shut_uper.services.openWeather.OpenWeatherService;
import com.gornitex.shell_shut_uper.services.openWeather.WeatherTableBuilder;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forecast")
@AllArgsConstructor
public class ForecastController {

	private final OpenWeatherService<ParsedWeather> openWeatherService;
	private final WeatherTableBuilder<ParsedWeather> weatherTableBuilder;

	@GetMapping("/weather/{cityName}")
	public List<ParsedWeather> weather(@PathVariable String cityName) {
		return openWeatherService.getForecast(cityName);
	}

	@GetMapping("/{cityName}")
	public List<ParsedWeather> weather(@PathVariable String cityName, @RequestParam int days) {
		return openWeatherService.getForecast(days, cityName);
	}


}
