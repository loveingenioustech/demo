package demo.redis.ecommerce.analytics.commands;

import java.util.Iterator;
import java.util.Set;

import demo.redis.ecommerce.Commands;
import demo.redis.ecommerce.util.AnalyticsDBManager;
import demo.redis.ecommerce.util.Argument;
import demo.redis.ecommerce.util.UserDBManager;

public class MyStatusCommand extends Commands
{

    public MyStatusCommand(Argument argument)
    {
        super(argument);
    }

    @Override
    public String execute()
    {
        System.out.println(this.getClass().getSimpleName() + ":  " + "Entering the execute function");

        String sessionID = this.getArgument().getValue("sessionid");

        if (UserDBManager.singleton.doesSessionExist(sessionID))
        {
            Set<String> browsingHistory = AnalyticsDBManager.singleton.getBrowsingHistory(sessionID);

            StringBuffer buffer = new StringBuffer();
            buffer.append(" View your browsing history where the one on top is the least visited product");
            buffer.append("\n and the product at the bottom is the most frequented product ");
            buffer.append("\n");

            Iterator<String> iterator = browsingHistory.iterator();
            int i = 1;
            while (iterator.hasNext())
            {
                buffer.append("[" + i + "] " + iterator.next() + "\n");
                i++;
            }

            System.out.println(this.getClass().getSimpleName() + ":  " + "Printing the result for execute function");
            System.out.println("Result = " + buffer.toString());
            return buffer.toString();
        }
        else
        {
            return "history is not available";
        }

    }

}
