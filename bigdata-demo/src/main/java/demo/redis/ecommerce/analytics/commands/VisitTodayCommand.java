package demo.redis.ecommerce.analytics.commands;

import demo.redis.ecommerce.Commands;
import demo.redis.ecommerce.util.AnalyticsDBManager;
import demo.redis.ecommerce.util.Argument;

public class VisitTodayCommand extends Commands
{

    public VisitTodayCommand(Argument argument)
    {
        super(argument);
    }

    @Override
    public String execute()
    {
        System.out.println(this.getClass().getSimpleName() + ":  " + "Entering the execute function");

        String productName = this.getArgument().getValue("productname");
        Integer visitCount = AnalyticsDBManager.singleton.getVisitToday(productName);

        System.out.println(this.getClass().getSimpleName() + ":  " + "Printing the result for execute function");
        System.out.println("Result = " + "Total Unique Visitors are: " + visitCount.toString());

        return "Total Unique Visitors are: " + visitCount.toString();
    }

}
