package demo.redis.ecommerce.sessionmgmt.commands;

import demo.redis.ecommerce.Commands;
import demo.redis.ecommerce.util.Argument;
import demo.redis.ecommerce.util.ShoppingCartDBManager;

public class BuyCommand extends Commands
{

    public BuyCommand(Argument argument)
    {
        super(argument);
    }

    @Override
    public String execute()
    {
        System.out.println(this.getClass().getSimpleName() + ":  " + " Entering the execute function");
        String sessionid = this.getArgument().getValue("sessionid");
        String shoppingdetails = ShoppingCartDBManager.singleton.buyItemsInTheShoppingCart(sessionid);
        return shoppingdetails;
    }

}
