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
public class AppsNamesValueProvider implements ValueProvider {

	private final Set<String> appsNames;

	public AppsNamesValueProvider(@Qualifier("appNames") Set<String> appsNames) {
		this.appsNames = appsNames;
	}

	@Override
	public boolean supports(MethodParameter methodParameter, CompletionContext completionContext) {
		return methodParameter.getParameterType().isAssignableFrom(String.class);
	}

	@Override
	public List<CompletionProposal> complete(MethodParameter methodParameter, CompletionContext completionContext, String[] strings) {
		return appsNames.stream().filter(x -> x.contains(completionContext.currentWordUpToCursor()))
				.map(CompletionProposal::new).collect(Collectors.toList());
	}


}