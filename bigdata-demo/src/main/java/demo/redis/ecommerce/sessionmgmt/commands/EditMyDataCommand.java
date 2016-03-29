package demo.redis.ecommerce.sessionmgmt.commands;

import java.util.Map;

import demo.redis.ecommerce.Commands;
import demo.redis.ecommerce.util.Argument;
import demo.redis.ecommerce.util.UserDBManager;

public class EditMyDataCommand extends Commands
{

    public EditMyDataCommand(Argument argument)
    {
        super(argument);
    }

    @Override
    public String execute()
    {
        System.out.println(this.getClass().getSimpleName() + ":  " + " Entering the execute function");

        Map<String, String> editMap = this.getArgument().getAttributes();
        boolean result = UserDBManager.singleton.editRegistrationMap(editMap);
        if (result)
        {
            return "Edit is Done....";
        }
        else
        {
            return "Edit not Done.... please check sessionid and name combination";
        }

    }

}
