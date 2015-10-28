package org.activiti.designer.test;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

/**
 * 外置表单测试
 * 表单的定义单独放在一个文件，在主流程定义中通过 activiti:formKey 指定表单的位置
 * 表单的内容都是以key和value的形式数据保存在引擎表中   
 * 类名称：ProcessTestFormKey   
 * 类描述：   
 * 创建时间：2015年10月28日 上午11:28:52   
 * 修改时间：2015年10月28日 上午11:28:52   
 * 修改备注：   
 * @version       
 */ 
public class ProcessTestFormKey extends PluggableActivitiTestCase
{

    @Deployment(resources = { "diagrams/form/FormKey.bpmn20.xml", "diagrams/form/start.form",
            "diagrams/form/first-step.form", "diagrams/form/second-step.form" })
    public void testTaskFormsWithVacationRequestProcess()
    {

        // Get start form
        String procDefId = repositoryService.createProcessDefinitionQuery().singleResult().getId();
        Object startForm = formService.getRenderedStartForm(procDefId);
        assertNotNull(startForm);

        assertEquals("<input id=\"start-name\" />", startForm);

        Map<String, String> formProperties = new HashMap<String, String>();
        formProperties.put("startName", "HenryYan");
        ProcessInstance processInstance = formService.submitStartFormData(procDefId, formProperties);
        assertNotNull(processInstance);

        Task task = taskService.createTaskQuery().taskAssignee("user1").singleResult();
        Object renderedTaskForm = formService.getRenderedTaskForm(task.getId());
        assertEquals("<input id=\"start-name\" value=\"HenryYan\" />\n<input id=\"first-name\" />", renderedTaskForm);

        formProperties = new HashMap<String, String>();
        formProperties.put("firstName", "kafeitu");
        formService.submitTaskFormData(task.getId(), formProperties);

        task = taskService.createTaskQuery().taskAssignee("user2").singleResult();
        assertNotNull(task);
        renderedTaskForm = formService.getRenderedTaskForm(task.getId());
        assertEquals("<input id=\"first-name\" value=\"HenryYan\" />", renderedTaskForm);
    }
}