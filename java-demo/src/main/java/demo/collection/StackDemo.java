package demo.collection;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class StackDemo
{
    public static void main(String[] args)
    {
        demoFolders();
    }

    private static void demoFolders()
    {
        SimpleStack directoryStack = new SimpleStack();
        File root = new File(File.separator + ".");

        directoryStack.push(root);

        while (directoryStack.size() > 0)
        {
            File currentDirectory = (File) directoryStack.pop();
            System.out.println(currentDirectory);
            String[] subdirectories = currentDirectory.list();
            if (subdirectories != null)
            {
                for (int i = 0; i < subdirectories.length; i++)
                {
                    try
                    {
                        String fname = currentDirectory.getCanonicalPath() + File.separator + subdirectories[i];
                        File f = new File(fname);

                        if (f.isDirectory())
                        {
                            directoryStack.push(f);
                        }

                    }
                    catch (IOException e)
                    {
                        System.out.println(e);
                    }
                }
            }
        }
    }
}

class SimpleStack
{
    private final List values;

    public SimpleStack()
    {
        values = new LinkedList();
    }

    public int size()
    {
        return values.size();
    }

    public void push(final Object object)
    {
        values.add(0, object);
    }

    public Object pop()
    {
        if (values.size() == 0)
        {
            return null;
        }
        return values.remove(0);
    }
}