package demo.drools.model.eshop;

import java.io.Serializable;

public class Manager implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String name;

    public Manager(String name)
    {
        this.name = name;
    }

    public Manager()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

}
