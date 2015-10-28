package org.activiti.designer.test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.util.ReflectUtil;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestTraininngProcess
{

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test
    public void startProcess() throws Exception
    {

        RepositoryService repositoryService = activitiRule.getRepositoryService();
        repositoryService.createDeployment().addInputStream("traininngProcess.bpmn20.xml",
                ReflectUtil.getResourceAsStream("diagrams/trainingRequest.bpmn")).deploy();

        Date date = new Date();
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("customerName", "Robin");
        variables.put("customerEamil", "Robin@test.com");
        variables.put("trainingTopic", "Activiti");
        variables.put("trainingDate", date);
        variables.put("trainerName", "Sean");
        variables.put("trainerMailId", "Sean@test.com");

        RuntimeService runtimeService = activitiRule.getRuntimeService();
        runtimeService.startProcessInstanceByKey("traininngProcess", variables);

        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();

        assertEquals("Business Development Executive", task.getName());

        taskService.complete(task.getId());
        assertEquals(0, runtimeService.createProcessInstanceQuery().count());
    }
}