package com.zzz.wzq.ui;

/**
 * Created by dell_2 on 2016/9/27.
 */
public class Test {

    public static void main(String[] args) {

        int a = 4;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j <a-i; j++) {
                System.out.print("\t");
            }
            int len = 2*i+1;
            for (int j = 0; j < len; j++) {
                System.out.print(Math.min(j,len-j-1));
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
