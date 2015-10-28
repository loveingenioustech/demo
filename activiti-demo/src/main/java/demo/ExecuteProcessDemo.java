package demo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.util.ReflectUtil;
import org.activiti.engine.runtime.ProcessInstance;

public class ExecuteProcessDemo
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
                .setJdbcDriver("com.mysql.jdbc.Driver").setJdbcUrl("jdbc:mysql://localhost:3306/activiti")
                .setJdbcPassword("").setJdbcUsername("root").buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        // Deploying The process into the repository
        repositoryService.createDeployment().addInputStream("trainingRequest.bpmn20.xml",
                ReflectUtil.getResourceAsStream("diagrams/trainingRequest.bpmn")).deploy();

        // Starting the Deployed Process
        Date date = new Date();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("customerName", "Robin");
        variables.put("customerEamil", "Robin@test.com");
        variables.put("trainingTopic", "Activiti");
        variables.put("trainingDate", date);

        RuntimeService runtimeService = processEngine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("traininngProcess", variables);

        System.out.println(processInstance.getId());
    }
}