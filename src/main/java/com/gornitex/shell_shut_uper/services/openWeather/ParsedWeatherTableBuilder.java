package com.gornitex.shell_shut_uper.services.openWeather;

import com.gornitex.shell_shut_uper.models.forecast.ParsedWeather;
import org.springframework.shell.table.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParsedWeatherTableBuilder implements WeatherTableBuilder<ParsedWeather> {

	@Override
	public Table buildTable(List<ParsedWeather> weatherList) {
		TableModel weatherTable = new BeanListTableModel<>(weatherList,
				"date", "time", "dayTime", "temperature", "feelsLike", "weatherState");
		return new TableBuilder(weatherTable.transpose()).addFullBorder(BorderStyle.fancy_light).build();
	}
}
