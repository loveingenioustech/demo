package demo.reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

interface InputModule {
    boolean isEnd();

    String readLine();
}

interface OutputModule {
    void writeLine(String toWrite);

    void closeOutput();
}

/**
 * 模拟工作流引擎
 * 输入输出，执行指定任务
 *
 * Created by Administrator on 2017-05-09.
 */
public class WorkflowEngine {
    private InputModule myInput;
    private OutputModule myOutput;
    private Properties myProps;

    public WorkflowEngine(String configPath) {
        try {
            loadPropertiesFromFile(configPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String inputModuleName = myProps.getProperty("input");
        String outputModuleName = myProps.getProperty("output");
        Class inputClass = null;
        Class outputClass = null;

        try {
            inputClass = Class.forName(inputModuleName);
            outputClass = Class.forName(outputModuleName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Check to make sure that in class implements proper Interface
        Class[] interfaces = inputClass.getInterfaces();
        boolean implemented = false;
        for (int i = 0; i < interfaces.length; i++) {
            if (interfaces[i].isAssignableFrom(inputClass))
                implemented = true;
        }
        if (!implemented)
            throw new RuntimeException("Given input doesn't implement InputModule");

        //Check to make sure that out class implements proper Interface
        interfaces = outputClass.getInterfaces();
        implemented = false;
        for (int i = 0; i < interfaces.length; i++) {
            if (interfaces[i].isAssignableFrom(outputClass))
                implemented = true;
        }
        if (!implemented)
            throw new RuntimeException("Given output doesn't implement InputModule");

        //Find the empty constructor, then use it to create new instances of input and output modules
        try {
            Constructor inputConstructor = inputClass.getConstructor(new Class[]{});
            myInput = (InputModule) inputConstructor.newInstance();

            Constructor outputConstructor = outputClass.getConstructor(new Class[]{});
            myOutput = (OutputModule) outputConstructor.newInstance();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 反转字符串
     */
    public void executeReverse() {
        while (!myInput.isEnd()) {
            myOutput.writeLine(reverse(myInput.readLine()));
        }
    }

    public static String reverse(String input) {
        return new StringBuffer(input).reverse().toString();
    }

    /**
     * 加载配置文件
     * @param fName
     * @throws IOException
     */
    private void loadPropertiesFromFile(String fName) throws IOException {
        myProps = new Properties();
        myProps.load(new FileInputStream(fName));
    }

}


