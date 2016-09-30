package com.zzz.draw.common.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dell_2 on 2016/9/24.
 */
public class ThreadUtil {

    public volatile static int  taskCount = 0;

    private  ExecutorService exec = Executors.newSingleThreadExecutor();



    public  void submit(Runnable task){
        System.out.println("添加一次任务!!!");
        taskCount ++;
        exec.submit(task);
    }

}
