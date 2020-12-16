package com.sys.clientpc.netty.listeners;

import io.socket.emitter.Emitter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @ClassName CloseScreenListener
 * @Author sys
 * @Date 2020/12/15 18:15
 * @Description 锁屏
 * @Version
 **/
@Slf4j
public class CloseScreenListener implements Emitter.Listener   {
    @Override
    public void call(Object... o) {
        log.debug("接收到服务端指令pc-close");
        String str = String.valueOf(o);
        try {
            switch (str) {
                case "local":
                    Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
//                case "local":
//                    Runtime.getRuntime().exec("shutdown -s -f -t 00");
//                case "local":
//                    Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
//                case "local":
//                    Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
//                case "local":
//                    Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
            }
            //向服务端响应结果
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exec("shutdown -s -f -t 00");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
