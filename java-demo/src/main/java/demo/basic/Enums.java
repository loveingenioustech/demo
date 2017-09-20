package demo.basic;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Enums {
	@NotNull
    private static Random rand = new Random(47);

	public static <T extends Enum<T>> T random(@NotNull Class<T> ec) {
		return random(ec.getEnumConstants());
	}

	public static <T> T random(@NotNull T[] values) {
		return values[rand.nextInt(values.length)];
	}
}
