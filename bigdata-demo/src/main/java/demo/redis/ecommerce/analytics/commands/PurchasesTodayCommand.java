package demo.redis.ecommerce.analytics.commands;

import demo.redis.ecommerce.Commands;
import demo.redis.ecommerce.util.Argument;
import demo.redis.ecommerce.util.ProductDBManager;

public class PurchasesTodayCommand extends Commands
{

    public PurchasesTodayCommand(Argument argument)
    {
        super(argument);
    }

    @Override
    public String execute()
    {
        System.out.println(this.getClass().getSimpleName() + ":  " + "Entering the execute function");
        String productName = this.getArgument().getValue("productname");
        Integer purchaseCount = ProductDBManager.singleton.getPurchaseToday(productName);

        System.out.println(this.getClass().getSimpleName() + ":  " + "Printing the result for execute function");
        System.out.println("Result = " + "Total Unique Customers are: " + purchaseCount.toString());

        return "Total Unique Customers are: " + purchaseCount.toString();
    }

}
