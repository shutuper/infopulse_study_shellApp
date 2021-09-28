package com.gornitex.shell_shut_uper.configuration;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class ShellPromptProvider implements PromptProvider {

	public static final String PROMPT = "Shut_uper:>";
	public static final String ASCI_BLACK = "\033[0;30m";
	public static final String ASCI_RED = "\033[0;31m";
	public static final String ASCI_GREEN = "\033[0;32m";
	public static final String ASCI_YELLOW = "\033[0;33m";
	public static final String ASCI_BLUE = "\033[0;34m";
	public static final String ASCI_PURPLE = "\033[0;35m";
	public static final String ASCI_CYAN = "\033[0;36m";
	public static final String ASCI_WHITE = "\033[0;37m";
	public static final String ASCI_RESET = "\033[0;37m";

	@Override
	public AttributedString getPrompt() {
		String coloredPrompt = ASCI_CYAN + PROMPT + ASCI_RESET;
		return new AttributedString(coloredPrompt); // \033[0m = RESET
	}
}
