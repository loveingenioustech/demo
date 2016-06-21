package demo.io;

import java.io.File;
import java.io.IOException;

public class FileDemo
{

    public static void main(String[] args)
    {
        demoDirectories();

    }

    /**
     * Exception in thread "main" java.lang.StackOverflowError
     * StackOverflowError是由于当前线程的栈满了，也就是函数调用层级过多导致。这里以为有.git，目录特别多。
     */
    private static void demoDirectories()
    {
        // start at the parent directory
        String[] roots = { ".." };

        try
        {
            File pathName = new File(roots[0]);
            String[] fileNames = pathName.list();

            // enumerate all files in the directory
            for (int i = 0; i < fileNames.length; i++)
            {
                File f = new File(pathName.getPath(), fileNames[i]);

                // if the file is again a directory, call the main method
                // recursively
                if (f.isDirectory())
                {
                    System.out.println(f.getCanonicalPath());
                    main(new String[] { f.getPath() });
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}
