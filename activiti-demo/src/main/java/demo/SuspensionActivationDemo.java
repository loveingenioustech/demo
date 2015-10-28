package demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;

public class SuspensionActivationDemo
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

        repositoryService.suspendProcessDefinitionByKey("traininngProcess");
        repositoryService.activateProcessDefinitionByKey("traininngProcess");
    }
}
