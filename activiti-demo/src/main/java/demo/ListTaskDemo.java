package demo;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class ListTaskDemo
{
    public static void main(String ar[])
    {
        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration()
                .setJdbcDriver("com.mysql.jdbc.Driver").setJdbcUrl("jdbc:mysql://localhost:3306/activiti")
                .setJdbcPassword("").setJdbcUsername("root").buildProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        List<Task> tasks = taskService.createTaskQuery().taskAssignee("gonzo").list();
        // .taskCandidateGroup("engineering").list();

        for (Task task : tasks)
        {
            System.out.println("Task available for Engineering group: " + task.getName());
        }
    }
}
