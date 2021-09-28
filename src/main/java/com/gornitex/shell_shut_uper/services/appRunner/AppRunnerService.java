package com.gornitex.shell_shut_uper.services.appRunner;

import com.gornitex.shell_shut_uper.configuration.ShellPromptProvider;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class AppRunnerService {

	private final Runtime RUNTIME_EXECUTOR;

	@Async
	public void runApp(String appName, String appPath) {
		try {
			RUNTIME_EXECUTOR.exec(appPath);
		} catch (IOException e) {
			System.out.println(ShellPromptProvider.ASCI_RED + "Couldn't run " + appName + " :(" + ShellPromptProvider.ASCI_RESET);
		}
	}

}
