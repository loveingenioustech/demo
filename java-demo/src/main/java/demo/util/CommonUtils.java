package demo.util;

import java.util.AbstractList;
import java.util.List;

public class CommonUtils {
	/* lower version of Eclipse will report error with java 1.8 */
	public static List<Integer> asList(final int[] numbers) {
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
