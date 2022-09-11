package com.cn.zfq;

/**
 * @author 众里阑珊
 */
public class OrderAndRandomRead {

    public static void main(String[] args) {
        int[][] arr = new int[50000][50000];
        int sum = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sum += arr[i][j];
            }
        }
        System.out.println("按行顺序访问耗时：" + (System.currentTimeMillis() - startTime) + " ms");
        sum = 0;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                sum += arr[j][i];
            }
        }
        System.out.println("按列随机访问耗时：" + (System.currentTimeMillis() - startTime) + " ms");
    }
}
