package demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * 获得配置
 * 
 * ProcessEngineConfiguration.
 * createProcessEngineConfigurationFromResourceDefault();
 * ProcessEngineConfiguration.createProcessEngineConfigurationFromResourc
 * e(String resource);
 * ProcessEngineConfiguration.createProcessEngineConfigurationFromResourc
 * e(String resource, String beanName);
 * ProcessEngineConfiguration.createProcessEngineConfigurationFromInputSt
 * ream(InputStream inputStream);
 * ProcessEngineConfiguration.createProcessEngineConfigurationFromInputSt
 * ream(InputStream inputStream, String beanName);
 * 
 * 创建 ProcessEngineprocessEngine
 * ProcessEngineprocessEngine = ProcessEngineConfiguration.
 * createStandaloneInMemProcessEngineConfiguration()
 * .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_
 * UPDATE_FALSE)
 * .setJdbcUrl("jdbc:h2:mem:my-own-db;DB_CLOSE_DELAY=1000")
 * .setJobExecutorActivate(true)
 * .buildProcessEngine();
 * 类名称：CreateCompleteDemo
 * 类描述：
 * 创建时间：2015年11月17日 下午5:44:00
 * 修改时间：2015年11月17日 下午5:44:00
 * 修改备注：
 * 
 * @version
 */
public class CreateCompleteDemo
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
                .setJdbcDriver("com.mysql.jdbc.Driver").setJdbcUrl("jdbc:mysql://localhost:3306/activiti")
                .setJdbcPassword("").setJdbcUsername("root").buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("gonzo").list();

        for (Task task : tasks)
        {
            System.out.println("Task available for Gonzo: " + task.getName());

        }

        Task task = tasks.get(0);
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("trainerName", "Robin");
        taskVariables.put("trainerMailId", "Robin@test.com");
        taskService.complete(task.getId(), taskVariables);
    }

}
