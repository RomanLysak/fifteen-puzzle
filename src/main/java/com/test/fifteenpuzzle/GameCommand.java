package com.test.fifteenpuzzle;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum GameCommand {
	NEW("n"), END("e"), LEFT("l"), RIGHT("r"), TOP("t"), DOWN("d"), UNKNOWN("unknown");

	private final String name;

	private static final Map<String, GameCommand> nameToCommandMap = Arrays.stream(values()).collect(Collectors.toMap(GameCommand::getName, Function.identity()));

	public static GameCommand of(String name) {
		return Optional.ofNullable(nameToCommandMap.get(name.toLowerCase())).orElse(UNKNOWN);
	}
}
