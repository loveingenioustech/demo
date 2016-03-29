package demo.redis.ecommerce.sessionmgmt.commands;

import java.util.Map;

import demo.redis.ecommerce.Commands;
import demo.redis.ecommerce.util.Argument;
import demo.redis.ecommerce.util.UserDBManager;

public class MyDataCommand extends Commands
{

    public MyDataCommand(Argument argument)
    {
        super(argument);
    }

    @Override
    public String execute()
    {
        System.out.println(this.getClass().getSimpleName() + ":  " + " Entering the execute function");
        String sessionid = this.getArgument().getValue("sessionid");

        String name = UserDBManager.singleton.getUserName(sessionid);
        Map<String, String> map = UserDBManager.singleton.getRegistrationMap(name);
        return map.toString();
    }

}
