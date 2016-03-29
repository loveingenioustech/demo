package demo.redis.ecommerce.productmgmt.commands;

import demo.redis.ecommerce.Commands;
import demo.redis.ecommerce.util.AnalyticsDBManager;
import demo.redis.ecommerce.util.Argument;

public class TagHistoryCommand extends Commands
{

    public TagHistoryCommand(Argument argument)
    {
        super(argument);
    }

    @Override
    public String execute()
    {
        String tagname = this.getArgument().getValue("tagname");
        String tagHistory = AnalyticsDBManager.singleton.getTagHistory(tagname);
        return tagHistory;
    }

}
