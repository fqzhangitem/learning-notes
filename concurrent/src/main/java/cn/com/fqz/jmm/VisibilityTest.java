package cn.com.fqz.jmm;

import cn.com.fqz.common.DateUtils;

/**
 * 用于对可见性的问题分析
 *
 * @author 众里阑珊
 */
public class VisibilityTest {

    /**
     * 使用 volatile关键字进行修饰
     */
    private Boolean flag = true;
    private int count = 0;

    /**
     * Integer 中是被final修饰
     */
//    private Integer count = 0;
    public void refresh() {
        flag = false;
        System.out.println(Thread.currentThread().getName() + "进行修改flag");
    }

    public void load() {
        System.out.println(Thread.currentThread().getName() + "开始执行");
        while (flag) {
            count++;
            // 假设进行实际业务逻辑 操作
            /**
             * 跳出循环的原因会去调内存屏障
             */
//            UnsafeFactory.getUnsafe().storeFence();
            /**
             * 是否时间片
             */

//            Thread.yield();

            /**
             * 在底层调用了synchronized
             */
//            System.out.println(count);

            /**
             * 发放一个许可，也是内存屏障
             */
//            LockSupport.unpark(Thread.currentThread());

            DateUtils.shortWait(1000000);


        }
        System.out.println(Thread.currentThread().getName() + "跳出循环：i=" + count);
    }

    public static void main(String[] args) throws InterruptedException {
        VisibilityTest test = new VisibilityTest();
        Thread threadA = new Thread(() -> test.load(), "threadA");
        threadA.start();
        Thread.sleep(1000);
        Thread threadB = new Thread(() -> test.refresh(), "threadB");
        threadB.start();
    }

}
