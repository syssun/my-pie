package com.sys.clientpc.netty.listeners;

import io.socket.emitter.Emitter;

import java.io.IOException;

/**
 * @ClassName CloseScreenListener
 * @Author sys
 * @Date 2020/12/15 18:15
 * @Description 锁屏
 * @Version
 **/
public class CloseScreenListener implements Emitter.Listener   {
    @Override
    public void call(Object... objects) {
        try {
            Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
