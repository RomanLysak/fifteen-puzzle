package com.test.fifteenpuzzle;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum GameCommand {
	NEW("n"), END("e"), LEFT("l"), RIGHT("r"), TOP("t"), DOWN("d"), UNKNOWN("unknown");

	GameCommand(String name) {
		this.name = name;
	}

	private final String name;

	public String getName() {
		return name;
	}

	private static final Map<String, GameCommand> nameToCommandMap = Arrays.stream(values()).collect(Collectors.toMap(GameCommand::getName, Function.identity()));

	public static GameCommand of(String name) {
		return Optional.ofNullable(nameToCommandMap.get(name.toLowerCase())).orElse(UNKNOWN);
	}
}
