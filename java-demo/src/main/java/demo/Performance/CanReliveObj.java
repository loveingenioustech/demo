package demo.Performance;

/**
 * Created by Administrator on 2017-08-01.
 */
public class CanReliveObj {
    public static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj=this;
        // 复活了对象
    }

    @Override
    public String toString(){
        return "I am CanReliveObj";
    }

    public static void main(String[] args) throws
            InterruptedException{
        obj=new CanReliveObj();
        obj=null;   //可复活
        System.gc();
        Thread.sleep(1000);
        if(obj==null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }

        System.out.println("第二次gc"); //   finalize方法至多由GC执行一次(用户当然可以手动调用对象的finalize方法，但并不影响GC对finalize的行为
        obj=null;    //不可复活
        System.gc();
        Thread.sleep(1000);
        if(obj==null){
            System.out.println("obj 是 null");
        }else{
            System.out.println("obj 可用");
        }
    }
}
