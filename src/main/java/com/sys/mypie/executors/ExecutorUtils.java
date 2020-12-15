package com.sys.mypie.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ExecutorUtils
 * @Author sys
 * @Date 2020/12/15 16:20
 * @Description //TODO
 * @Version
 **/
public class ExecutorUtils {
    private static ExecutorUtils executorUtils =new ExecutorUtils();
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    private  void execute(Runnable run){
        if(executorUtils==null){
            executorUtils = new ExecutorUtils();
        }
        fixedThreadPool.execute(run);
    }

    public static void submit(Runnable run){
        executorUtils.execute(run);
    }
}
