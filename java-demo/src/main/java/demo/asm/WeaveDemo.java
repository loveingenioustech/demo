package demo.asm;

/**
 * Created by Administrator on 2017-08-09.
 */
public class WeaveDemo {
    public static void main(String args[]){
        Account account = new Account();

        account.operation();
        // 首先直接运行 WeaveDemo 只输出operation....
        // 然后运行 Generator 输出:
        // SecurityChecker.checkSecurity ...
        // operation....
    }
}
