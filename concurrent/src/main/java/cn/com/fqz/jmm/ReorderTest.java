package cn.com.fqz.jmm;

import cn.com.fqz.common.DateUtils;
import cn.com.fqz.common.UnsafeFactory;

/**
 * @author 张枫琴
 */
public class ReorderTest {
    private static  int x = 0, y = 0;
    private static  int a = 0, b = 0;
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;

            /**
             * x,y
             */
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    DateUtils.shortWait(1000);
                    UnsafeFactory.getUnsafe().storeFence();
                    a = 1;
                    x = b;
                }
            });

            /**
             * a,b
             */
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    DateUtils.shortWait(1000);
                    UnsafeFactory.getUnsafe().storeFence();
                    b = 1;
                    y = a;
                }
            });

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

            System.out.println("第" + i + "次(" + x + "," + y + ")");
            if (x == 0 && y == 0) {
                break;
            }
        }
    }
}
