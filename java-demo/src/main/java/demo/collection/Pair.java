package demo.collection;

import org.jetbrains.annotations.Nullable;

public class Pair<T>
{
    @Nullable
    private T first;
    @Nullable
    private T second;

    public Pair()
    {
        first = null;
        second = null;
    }

    public Pair(T first, T second)
    {
        this.first = first;
        this.second = second;
    }

    @Nullable
    public T getFirst()
    {
        return first;
    }

    public void setFirst(T first)
    {
        this.first = first;
    }

    @Nullable
    public T getSecond()
    {
        return second;
    }

    public void setSecond(T second)
    {
        this.second = second;
    }

}
