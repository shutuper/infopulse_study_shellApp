package com.gornitex.shell_shut_uper.models.forecast;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParsedWeather {

	private String date;
	private String time;
	private String dayTime;
	private String temperature;
	private String feelsLike;
	private String weatherState;

}
