package demo.aop;

import org.aspectj.lang.JoinPoint;

/**
 * 类名称：POJOAdvice
 * 类描述：这里的Advice 无需实现MethodBeforeAdvice等接口，所以加之已POJO，后面通过XML配置来完成切入
 * 创建时间：2015-8-28 上午8:58:02
 * 修改时间：2015-8-28 上午8:58:02
 * 修改备注：
 * 
 * @version
 */
public class POJOAdvice
{
    public void simpleBeforeAdvice(JoinPoint joinPoint)
    {
        System.out.println("Executing: " + joinPoint.getSignature().getDeclaringTypeName() + " "
                + joinPoint.getSignature().getName());
    }

}
