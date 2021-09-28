package com.gornitex.shell_shut_uper.services.openWeather;

import org.springframework.shell.table.Table;
import org.springframework.stereotype.Component;

import java.util.List;


public interface WeatherTableBuilder<T> {

	Table buildTable(List<T> weatherList);

}
