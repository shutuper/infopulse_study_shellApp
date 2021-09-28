package com.gornitex.shell_shut_uper.controllers.shell;


import com.gornitex.shell_shut_uper.configuration.ShellPromptProvider;
import com.gornitex.shell_shut_uper.services.appRunner.AppRunnerService;
import com.gornitex.shell_shut_uper.services.shellValueProviders.AppsNamesValueProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
@ShellComponent
public class AppRunnerShell {

	private final Properties getAppsPathsProp;
	private final AppRunnerService appRunnerService;

	public AppRunnerShell(@Qualifier("appsPaths") Properties getAppsPathsProp, AppRunnerService appRunnerService) {
		this.appRunnerService = appRunnerService;
		this.getAppsPathsProp = getAppsPathsProp;
	}

	@ShellMethod(value = "Run some apps.", key = {"run", "r", "start", "go"})
	public String runApps(@ShellOption(valueProvider = AppsNamesValueProvider.class) String appsNames) {
		String[] appsNamesArr = appsNames.split("\\s+");
		System.out.println(Arrays.toString(appsNamesArr));
		for (String key : appsNamesArr)
			if (getAppsPathsProp.containsKey(key)) appRunnerService.runApp(key, getAppsPathsProp.getProperty(key));

		return String.join(", ", appsNamesArr) + " are launching! :)";
	}

}

