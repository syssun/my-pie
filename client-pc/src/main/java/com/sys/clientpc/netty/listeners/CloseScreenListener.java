package com.sys.clientpc.netty.listeners;

import ch.qos.logback.core.db.dialect.MySQLDialect;
import com.sys.clientpc.netty.SocketIOClient;
import com.sys.clientpc.utils.SpringContextHolder;
import com.sys.clientpc.webcam.MyWebcam;
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
    SocketIOClient socketIOClient  = SocketIOClient.getInstance();
    @Override
    public void call(Object... o) {
        if(o==null){
            return;
        }
        String str = (String) o[0];
        log.info("接收到服务端指令 pc-close "+str);
        try {
            String res = "1";
            switch (str) {
                case "lock_screen":
                    Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
                    break;
                case "shutdown_now":
                    Runtime.getRuntime().exec("shutdown -s -f -t 00");
                    break;
                case "shutdown60":
                    Runtime.getRuntime().exec("shutdown -s -f -t 60");
                    break;
                case "cancel_shutdown":
                    Runtime.getRuntime().exec("shutdown -a");
                    break;
                case "sleep":
                    Runtime.getRuntime().exec("shutdown -h");
                    break;
                case "take_photo":
                    res = MyWebcam.getImageBase64();
                    break;
            }
            //向服务端响应结果
            socketIOClient.getSocket().emit("pc-close-res",res);
        } catch (IOException e) {
            e.printStackTrace();
            socketIOClient.getSocket().emit("pc-close-res","0");
        }
    }

}
