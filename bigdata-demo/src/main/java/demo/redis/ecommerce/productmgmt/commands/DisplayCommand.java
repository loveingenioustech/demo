package demo.redis.ecommerce.productmgmt.commands;

import demo.redis.ecommerce.Commands;
import demo.redis.ecommerce.util.Argument;
import demo.redis.ecommerce.util.ProductDBManager;

public class DisplayCommand extends Commands
{

    public DisplayCommand(Argument argument)
    {
        super(argument);
    }

    @Override
    public String execute()
    {
        String display = ProductDBManager.singleton.getProductInfo(this.getArgument().getValue("name"));
        return display;
    }

}
