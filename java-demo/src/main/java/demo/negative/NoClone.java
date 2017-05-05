package demo.negative;

import java.awt.*;

/**
 * Created by Administrator on 2017-05-05.
 */
public class NoClone {
    public static void main(String args[]){
        Example ex = new Example();
        Dimension d = ex.getValues();

        // Example对象拥有负值了！如果getValues() 的调用者永远也不设置返回的Dimension对象的width 和height值，那么仅凭测试是不可能检测到这类的错误。
        d.height = -5;
        d.width = -10;

        System.out.println("d.height = " + d.height);
        System.out.println("d.width = " + d.width);
    }

}

/**
 * 封装(encapsulation)是面向对象编程的重要概念。不幸的是，Java为不小心打破封装提供了方便——Java允许返回私有数据的引用(reference)。
 *
 */
class Example{
    private Dimension d = new Dimension (0, 0);
    public Example (){ }

    /*** Set height and width. Both height and width must be nonnegative * or an exception is thrown.*/
    public synchronized void setValues (int height,int width) throws IllegalArgumentException{
        if (height < 0 || width < 0)
            throw new IllegalArgumentException();
        d.height = height;
        d.width = width;
    }

    /**
     * Example类保证了它所存储的height和width值永远非负数，试图使用setValues()方法来设置负值会触发异常。不幸的是，由于getValues()返回d的引用，而不是d的拷贝
     *
     * @return
     */
    public synchronized Dimension getValues(){
        // Ooops! Breaks encapsulation
        return d;
    }

// 解决方案，拷贝一个新的对象
//    public synchronized Dimension getValues(){
//        return new Dimension (d.x, d.y);
//    }
}
