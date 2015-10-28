package demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

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
