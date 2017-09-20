package demo.Performance;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * Created by Administrator on 2017-08-01.
 */
public class MyThread extends Thread {
    @NotNull
    HashMap<Long,byte[]> map=new HashMap<Long,byte[]>();
    @Override
    public void run(){
        try{
            while(true){
                if(map.size()*512/1024/1024>=450){
                    System.out.println("=====准备清理=====:"+map.size());
                    map.clear();
                }

                for(int i=0;i<1024;i++){
                    map.put(System.nanoTime(), new byte[512]);
                }
                Thread.sleep(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
