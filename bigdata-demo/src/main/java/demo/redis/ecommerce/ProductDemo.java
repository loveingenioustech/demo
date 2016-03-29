package demo.redis.ecommerce;

import demo.redis.ecommerce.analytics.commands.PurchasesTodayCommand;
import demo.redis.ecommerce.analytics.commands.VisitTodayCommand;
import demo.redis.ecommerce.productmgmt.commands.CommissionProductCommand;
import demo.redis.ecommerce.productmgmt.commands.DisplayCommand;
import demo.redis.ecommerce.productmgmt.commands.DisplayTagCommand;
import demo.redis.ecommerce.productmgmt.commands.TagHistoryCommand;
import demo.redis.ecommerce.productmgmt.commands.UpdateTagCommand;
import demo.redis.ecommerce.util.Argument;

public class ProductDemo
{

    public static void main(String[] args)
    {

        // 测试提交产品
        // String command = "commission";
        // Argument argument = new Argument(
        // "name=Redisbook-1:cost=10:catagory=book:author=vinoo:tags=Redis@5,NoSql@3,database@2,technology@1");

        // 测试显示产品
        // String command = "display";
        // Argument argument = new Argument("name=Redisbook-1");

        // 按Tag展示
        // String command = "displaytag";
        // Argument argument = new Argument("tagname=nosql");

        String command = "displaytag";
        Argument argument = new Argument("tagname=nosql");

        switch (command.toLowerCase())
        {
            case "commission":
                Commands commission = new CommissionProductCommand(argument);
                System.out.println(commission.execute());
                break;
            case "display":
                Commands display = new DisplayCommand(argument);
                System.out.println(display.execute());
                break;
            case "displaytag":
                Commands displaytag = new DisplayTagCommand(argument);
                System.out.println(displaytag.execute());
                break;
            case "updatetag":
                Commands updatetag = new UpdateTagCommand(argument);
                System.out.println(updatetag.execute());
                break;

            case "visitstoday":
                Commands visittoday = new VisitTodayCommand(argument);
                System.out.println(visittoday.execute());
                break;

            case "purchasestoday":
                Commands purchasestoday = new PurchasesTodayCommand(argument);
                System.out.println(purchasestoday.execute());
                break;

            case "taghistory":
                Commands taghistory = new TagHistoryCommand(argument);
                System.out.println(taghistory.execute());
                break;
            default:
                Commands defaultUC = new DefaultCommand(argument);
                System.out.println(defaultUC.execute());
                break;
        }

    }

}
