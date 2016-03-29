package demo.redis.ecommerce.sessionmgmt.commands;

import demo.redis.ecommerce.Commands;
import demo.redis.ecommerce.util.Argument;
import demo.redis.ecommerce.util.UserDBManager;

public class ReloginCommand extends Commands
{

    public ReloginCommand(Argument argument)
    {
        super(argument);
    }

    @Override
    public String execute()
    {
        System.out.println(this.getClass().getSimpleName() + ":  " + " Entering the execute function");

        String name = this.getArgument().getValue("name");
        String password = this.getArgument().getValue("password");

        if (UserDBManager.singleton.doesUserExist(name))
        {
            if (UserDBManager.singleton.getUserPassword(name).equals(password))
            {
                String sessionID = UserDBManager.singleton.getUserSessionId(name);
                return "ReLogin successful \n" + name + " \n use the following session id : " + sessionID;

            }
            else
            {
                return " ReLogin failed ...invalid password ";
            }

        }
        else
        {
            return " please register before executing command for login ";
        }
    }

}
