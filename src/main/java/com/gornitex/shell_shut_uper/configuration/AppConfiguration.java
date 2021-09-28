package com.gornitex.shell_shut_uper.configuration;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.gornitex.shell_shut_uper.models.forecast.ParsedWeather;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class AppConfiguration {

	@Bean("appsPaths")
	public Properties appsPathsFromProperties() throws IOException {
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream("/appRunner/appsPaths.properties"));
		//properties.entrySet().forEach(x-> System.out.println(x.getKey()+" "+x.getValue()));
		return properties;
	}

	@Bean("appNames")
	public Set<String> appsNamesFromProperties() throws IOException {
		return appsPathsFromProperties().keySet().stream().map(o -> (String) o).collect(Collectors.toSet());
	}

	@Bean("cityNames")
	public Set<String> cityNames() {
		return Set.of("Nizhyn", "Moscow", "Kyiv", "Minsk", "Chernihiv", "Odessa");
	}

	@Bean("weatherTableHeaders")
	public ParsedWeather weatherTableHeaders() {
		return new ParsedWeather("Date", "Time",
				"Daytime", "Temperature", "Feels like", "Weather state");
	}

	@Bean("OpenWeatherMapClient")
	public OpenWeatherMapClient openWeatherMapClientProvider() {
		return new OpenWeatherMapClient("secret");
	}

	@Bean("runtime")
	public Runtime runtimeProvider() {
		return Runtime.getRuntime();
	}
}
