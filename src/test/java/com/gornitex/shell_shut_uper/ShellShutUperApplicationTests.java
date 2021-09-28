package com.gornitex.shell_shut_uper;

import com.gornitex.shell_shut_uper.services.openWeather.OpenWeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShellShutUperApplicationTests {

	private final OpenWeatherService openWeatherService;

	@Autowired
	ShellShutUperApplicationTests(OpenWeatherService openWeatherService) {
		this.openWeatherService = openWeatherService;
	}


	@Test
	void contextLoads() {
		System.out.println(openWeatherService.getForecast("Nizhyn"));
		System.out.println(openWeatherService.getForecast("Kiev"));
		System.out.println(openWeatherService.getForecast("Moscow"));
	}

}
