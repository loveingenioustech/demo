package demo.aop;

public class SimpleBeanImpl implements SimpleBean
{
    private long dummy = 0;

    @Override
    public void advised()
    {
        dummy = System.currentTimeMillis();
    }

    @Override
    public void unadvised()
    {
        dummy = System.currentTimeMillis();
    }

}
