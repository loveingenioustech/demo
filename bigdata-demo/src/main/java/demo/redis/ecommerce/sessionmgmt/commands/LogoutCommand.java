package demo.redis.ecommerce.sessionmgmt.commands;

import demo.redis.ecommerce.Commands;
import demo.redis.ecommerce.util.Argument;
import demo.redis.ecommerce.util.UserDBManager;

public class LogoutCommand extends Commands
{

    public LogoutCommand(Argument argument)
    {
        super(argument);
    }

    @Override
    public String execute()
    {
        System.out.println(this.getClass().getSimpleName() + ":  " + " Entering the execute function");

        String sessionid = this.getArgument().getValue("sessionid");
        if (UserDBManager.singleton.expireSession(sessionid))
        {
            return "logout was clean";
        }
        else
        {
            return "logout was not clean";
        }

    }

}
