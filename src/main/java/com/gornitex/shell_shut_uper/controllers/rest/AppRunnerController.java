package com.gornitex.shell_shut_uper.controllers.rest;


import com.gornitex.shell_shut_uper.services.appRunner.AppRunnerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Properties;

@RestController()
@RequestMapping("/run")
public class AppRunnerController {

	private final AppRunnerService appRunnerService;
	private final Properties getAppsPathsProp;

	public AppRunnerController(@Qualifier("appsPaths") Properties getAppsPathsProp, AppRunnerService appRunnerService) {
		this.appRunnerService = appRunnerService;
		this.getAppsPathsProp = getAppsPathsProp;
	}

	@GetMapping("/{appNames}")
	public String runApp(@PathVariable String[] appNames) {
		System.out.println(Arrays.toString(appNames));
		for (String key : appNames)
			if (getAppsPathsProp.containsKey(key)) appRunnerService.runApp(key, getAppsPathsProp.getProperty(key));

		return String.join(", ", appNames) + " are launching! :)";
	}

}
