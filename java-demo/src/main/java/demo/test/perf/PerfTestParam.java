package demo.test.perf;

/**
 * 类名称：PerfPerfTestParam
 * 类描述：用于数据传输 A "data transfer object."
 * 创建时间：2016年4月25日 上午11:32:13
 * 修改时间：2016年4月25日 上午11:32:13
 * 修改备注：
 * 
 * @version
 */
public class PerfTestParam
{
    // 数量
    public final int size;
    // 循环次数
    public final int loops;

    public PerfTestParam(int size, int loops)
    {
        this.size = size;
        this.loops = loops;
    }

    /**
     * 在Java5
     * 中提供了变长参数（varargs），也就是在方法定义中可以使用个数不确定的参数，对于同一方法可以使用不同个数的参数调用
     * 例如print("hello");print("hello","lisi");print("hello","张三", "alexia");
     * Create an array of PerfTestParam from a varargs sequence
     * 
     * @param values
     * @return
     */
    public static PerfTestParam[] array(int... values)
    {
        int size = values.length / 2;
        PerfTestParam[] result = new PerfTestParam[size];
        int n = 0;
        for (int i = 0; i < size; i++)
        {
            result[i] = new PerfTestParam(values[n++], values[n++]);
        }

        return result;
    }

    /**
     * Convert a String array to a PerfTestParam array
     * 
     * @param values
     * @return
     */
    public static PerfTestParam[] array(String[] values)
    {
        int[] vals = new int[values.length];
        for (int i = 0; i < vals.length; i++)
        {
            vals[i] = Integer.decode(values[i]);
        }

        return array(vals);
    }

}
