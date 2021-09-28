package com.gornitex.shell_shut_uper.services.shellValueProviders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.shell.CompletionContext;
import org.springframework.shell.CompletionProposal;
import org.springframework.shell.standard.ValueProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CityNamesValueProvider implements ValueProvider {

	private final Set<String> cityNames;

	public CityNamesValueProvider(@Qualifier("cityNames") Set<String> cityNames) {
		this.cityNames = cityNames;
	}

	@Override
	public boolean supports(MethodParameter methodParameter, CompletionContext completionContext) {
		return methodParameter.getParameterType().isAssignableFrom(String.class);
	}

	@Override
	public List<CompletionProposal> complete(MethodParameter methodParameter, CompletionContext completionContext, String[] strings) {
		return cityNames.stream()
				.filter(x -> x.toLowerCase().contains(completionContext.currentWordUpToCursor()))
				.map(CompletionProposal::new).collect(Collectors.toList());
	}
}
