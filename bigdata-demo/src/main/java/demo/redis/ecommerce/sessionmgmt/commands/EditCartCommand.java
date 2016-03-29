package demo.redis.ecommerce.sessionmgmt.commands;

import java.util.HashMap;
import java.util.Map;

import demo.redis.ecommerce.Commands;
import demo.redis.ecommerce.util.Argument;
import demo.redis.ecommerce.util.ShoppingCartDBManager;
import demo.redis.ecommerce.util.UserDBManager;

public class EditCartCommand extends Commands
{

    public EditCartCommand(Argument argument)
    {
        super(argument);
    }

    @Override
    public String execute()
    {
        System.out.println(this.getClass().getSimpleName() + ":  " + " Entering the execute function");

        String result = "did not edit the shopping cart";
        String sessionID = this.getArgument().getValue("sessionid");
        String product = this.getArgument().getValue("product");

        String[] productList = product.split(",");
        Map<String, String> productQtyMap = new HashMap<String, String>();

        for (String _product : productList)
        {

            String[] nameQty = _product.split("@");
            productQtyMap.put(nameQty[0], nameQty[1]);
        }

        if (UserDBManager.singleton.doesSessionExist(sessionID))
        {
            result = ShoppingCartDBManager.singleton.editMyCart(sessionID, productQtyMap);
        }
        return "result : " + result;

    }

}
