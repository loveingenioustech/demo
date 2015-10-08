package demo.test.junit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Executor.class)
public class ExecutorTest {

	@Execute
	public void doIt() {
	}

	@Execute
	public void doItWithProblem() {
		throw new RuntimeException("Bad");
	}

	@Execute
	public void doItWithFailure() {
		throw new AssertionError("Invalid");
	}

}
