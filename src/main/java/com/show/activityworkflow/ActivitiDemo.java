package com.show.activityworkflow;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhengfawei
 * @create 2019-11-26 下午3:51
 * @desc
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiDemo {
    @Resource
    ProcessEngine engineEngine ;//注入流程引擎

    @Resource
    RepositoryService repositoryService;

    @Resource
    RuntimeService runtimeService;

    @Resource
    TaskService taskService;

    @Resource
    HistoryService historyService;

    /**
     * 部署流程实例
     */
    @Test
    public void deploy(){

        // 部署流程文件
        DeploymentBuilder builder = engineEngine.getRepositoryService().createDeployment();
        Deployment deploy = builder.name("test").addClasspathResource("processes/test.bpmn").addClasspathResource("processes/test.png").deploy();
        System.out.println("部署完成\n"+deploy.getId());
        System.out.println("----------------");
        // 启动流程
    }
    /**
     * 启动流程实例
     */
    @Test
    public void start(){
        String processDefinitionKey ="myProcess";//对应bpmn中的id
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        System.out.println(pi.getProcessDefinitionId());
        System.out.println(pi.getProcessDefinitionKey());
        System.out.println(pi.getId());
    }
    /**
     * 查询个人任务相关
     */
    @Test
    public void searchTask(){
//        String assigenee="张三";
        String assigenee="李四";
        List<Task> list = taskService.createTaskQuery().taskAssignee(assigenee).list();
        if(list!=null&& list.size()>0){
            for (Task task:list) {
                System.out.println(task.getId());
                System.out.println(task.getName());
                System.out.println(task.getAssignee());
            }
        }
    }

    /**
     * 完成个人任务相关
     */
    @Test
    public void completeTask(){
        String taskId="12502";
        taskService.complete(taskId);
        System.out.println("完成任务"+taskId);
    }
//    @Test
//    public void test(){
//
//        // 部署流程文件
//        DeploymentBuilder builder = engineEngine.getRepositoryService().createDeployment();
//
//        Deployment deploy = builder.name("test").addClasspathResource("processes/test.bpmn").addClasspathResource("processes/test.png").deploy();
//
//        System.out.println("部署完成\n"+deploy.getId());
//        System.out.println("----------------");
//        // 启动流程
//    }
}
