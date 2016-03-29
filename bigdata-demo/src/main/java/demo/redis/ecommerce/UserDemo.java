package demo.redis.ecommerce;

import demo.redis.ecommerce.analytics.commands.MyStatusCommand;
import demo.redis.ecommerce.analytics.commands.RecomendByProductCommand;
import demo.redis.ecommerce.sessionmgmt.commands.Add2CartCommand;
import demo.redis.ecommerce.sessionmgmt.commands.BrowseCommand;
import demo.redis.ecommerce.sessionmgmt.commands.BuyCommand;
import demo.redis.ecommerce.sessionmgmt.commands.EditCartCommand;
import demo.redis.ecommerce.sessionmgmt.commands.EditMyDataCommand;
import demo.redis.ecommerce.sessionmgmt.commands.LoginCommand;
import demo.redis.ecommerce.sessionmgmt.commands.LogoutCommand;
import demo.redis.ecommerce.sessionmgmt.commands.MyDataCommand;
import demo.redis.ecommerce.sessionmgmt.commands.MyPurchaseHistoryCommand;
import demo.redis.ecommerce.sessionmgmt.commands.RegistrationCommand;
import demo.redis.ecommerce.sessionmgmt.commands.ReloginCommand;
import demo.redis.ecommerce.sessionmgmt.commands.ShowMyCartCommand;
import demo.redis.ecommerce.util.Argument;

public class UserDemo
{

    public static void main(String[] args)
    {
        // 测试注册
        // String command = "register";
        // Argument argument = new
        // Argument("name=robin:password=123456:address=test address");

        String command = "login";
        Argument argument = new Argument("name=robin:password=123456");

        switch (command.toLowerCase())
        {
            case "register":
                Commands register = new RegistrationCommand(argument);
                System.out.println(register.execute());
                break;

            case "login":
                Commands login = new LoginCommand(argument);
                System.out.println(login.execute());
                break;

            case "mydata":
                Commands mydata = new MyDataCommand(argument);
                System.out.println(mydata.execute());
                break;
            case "editmydata":
                Commands editMyData = new EditMyDataCommand(argument);
                System.out.println(editMyData.execute());
                break;

            case "recommendbyproduct":
                Commands recommendbyproduct = new RecomendByProductCommand(argument);
                String recommendbyproducts = recommendbyproduct.execute();
                System.out.println(recommendbyproducts);
                break;

            case "browse":
                Commands browse = new BrowseCommand(argument);
                String result = browse.execute();
                System.out.println(result);
                String productname = argument.getValue("browse");
                String sessionid = argument.getValue("sessionid");
                System.out.println("productname: " + productname + " sessionid: " + sessionid);
                break;

            case "buy":
                Commands buy = new BuyCommand(argument);
                String[] details = buy.execute().split("#");
                System.out.println(details[0]);
                String sessionID = argument.getValue("sessionid");
                System.out.println(" sessionid: " + sessionID);
                break;

            case "stats":
                Commands stats = new MyStatusCommand(argument);
                System.out.println(stats.execute());
                break;

            case "add2cart":
                Commands add2cart = new Add2CartCommand(argument);
                System.out.println(add2cart.execute());
                break;

            case "showmycart":
                Commands showmycart = new ShowMyCartCommand(argument);
                System.out.println(showmycart.execute());
                break;

            case "editcart":
                Commands editCard = new EditCartCommand(argument);
                System.out.println(editCard.execute());
                break;

            case "relogin":
                Commands relogin = new ReloginCommand(argument);
                System.out.println(relogin.execute());
                break;

            case "logSystem.out":
                Commands logout = new LogoutCommand(argument);
                System.out.println(logout.execute());
                break;

            case "mypurchasehistory":
                Commands mypurchasehistory = new MyPurchaseHistoryCommand(argument);
                System.out.println(mypurchasehistory.execute());
                break;

            default:
                Commands defaultUC = new DefaultCommand(argument);
                System.out.println(defaultUC.execute());
                break;
        }

    }

}
