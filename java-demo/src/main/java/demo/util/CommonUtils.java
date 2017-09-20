package demo.util;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractList;
import java.util.List;

public class CommonUtils {
	/* lower version of Eclipse will report error with java 1.8 */
	@NotNull
    public static List<Integer> asList(@NotNull final int[] numbers) {
		return new AbstractList<Integer>() {
			public Integer get(int i) {
				return numbers[i];
			}

			public int size() {
				return numbers.length;
			}
		};
	}

}
