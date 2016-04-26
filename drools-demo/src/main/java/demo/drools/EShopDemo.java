package demo.drools;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import demo.drools.model.eshop.Item;

public class EShopDemo
{
    public static void main(String[] args)
    {
        System.out.println("Bootstrapping the Rule Engine ...");
        // Bootstrapping a Rule Engine Session
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("demo");

        // before fire rule
        Item item = new Item("A", 123.0, 234.0);
        System.out.println("Item Category: " + item.getCategory());
        kSession.insert(item);

        // fire rule
        int fired = kSession.fireAllRules();

        // after fire rule
        System.out.println("Number of Rules executed = " + fired);
        System.out.println("Item Category: " + item.getCategory());
    }
}
