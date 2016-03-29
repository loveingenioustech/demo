package demo.redis.ecommerce.productmgmt.commands;

import demo.redis.ecommerce.Commands;
import demo.redis.ecommerce.util.Argument;
import demo.redis.ecommerce.util.ProductDBManager;

public class DisplayTagCommand extends Commands
{

    public DisplayTagCommand(Argument argument)
    {
        super(argument);
    }

    @Override
    public String execute()
    {
        System.out.println(this.getClass().getSimpleName() + ":  " + " Entering the execute function");

        String tagName = this.getArgument().getValue("tagname");
        String details = ProductDBManager.singleton.getTagValues(tagName);
        return details;
    }

}
