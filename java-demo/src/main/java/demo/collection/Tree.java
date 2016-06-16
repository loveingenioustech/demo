package demo.collection;

public class Tree<E extends Comparable>
{
    private E value;
    private Tree<E> left;
    private Tree<E> right;

    // constructors, getters and setters omitted
    public Tree(E toInsert, Object object, Object object2)
    {
        // TODO Auto-generated constructor stub
    }

    public boolean search(final E toFind)
    {
        if (toFind.equals(value))
        {
            return true;
        }
        if (toFind.compareTo(value) < 0 && left != null)
        {
            return left.search(toFind);
        }
        return right != null && right.search(toFind);
    }

    public void insert(final E toInsert)
    {
        if (toInsert.compareTo(value) < 0)
        {
            if (left == null)
            {
                left = new Tree<>(toInsert, null, null);
            }
            else
            {
                left.insert(toInsert);
            }
        }
        else
        {
            if (right == null)
            {
                right = new Tree<>(toInsert, null, null);
            }
            else
            {
                right.insert(toInsert);
            }
        }
    }
}
