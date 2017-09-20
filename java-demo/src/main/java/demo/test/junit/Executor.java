package demo.test.junit;

import static org.junit.runner.Description.createSuiteDescription;
import static org.junit.runner.Description.createTestDescription;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;

public class Executor extends Runner {
	private final List<FrameworkMethod> methods;
	@NotNull
    private final TestClass meta;

	public Executor(Class<?> testType) {
		meta = new TestClass(testType);
		methods = meta.getAnnotatedMethods(Execute.class);
	}

	@NotNull
    @Override
	public Description getDescription() {
		Description result = createClassDescription();

		methods.forEach(method -> result.addChild(createMethodDescription(method)));
		return result;
	}

	@Override
	public void run(@NotNull RunNotifier notifier) {
		methods.forEach(method -> run(notifier, method));
	}

	private void run(@NotNull RunNotifier notifier, @NotNull FrameworkMethod method) {
		Description description = createMethodDescription(method);
		notifier.fireTestStarted(description);
		
		try {
			Object target = meta.getJavaClass().newInstance();
			method.invokeExplosively(target);
		} catch (Throwable problem) {
			Failure failure = new Failure(description, problem);
			notifier.fireTestFailure(failure);
		}
		
		notifier.fireTestFinished(description);
	}

	private Description createClassDescription() {
		String name = meta.getName();
		Execute annotations = meta.getAnnotation(Execute.class);
		return createSuiteDescription(name, annotations);
	}

	private Description createMethodDescription(@NotNull FrameworkMethod method) {
		return createTestDescription(meta.getClass(), method.getName());
	}

}
