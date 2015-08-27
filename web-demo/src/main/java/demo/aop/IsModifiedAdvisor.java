package demo.aop;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class IsModifiedAdvisor extends DefaultIntroductionAdvisor
{
    /**  
     * @Fields serialVersionUID  
     */
    private static final long serialVersionUID = 8345048792359710449L;

    public IsModifiedAdvisor()
    {
        super(new IsModifiedMixin());    
    }

}
