package com.sys.clientpc.netty;

import com.sys.clientpc.netty.listeners.BroadcastListener;
import com.sys.clientpc.netty.listeners.CloseScreenListener;
import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @ClassName SocketIOClient
 * @Author sys
 * @Date 2020/12/15 16:52
 * @Description //TODO
 * @Version
 **/
@Slf4j
@Component
public class SocketIOClient {
    @Value("${netty.server.url}")
    private String url;

    private  Socket socket;
    private static String userID = "PC-"+(new Date().getTime());

    public Socket getSocket(){
        if(socket==null || !socket.connected()){
            log.info("尝试连接一次");
            clientConnect();
        }

        return socket;
    }
    @Scheduled(cron = "0/5 * * * * *")
    public void clientConnect() {
        // 服务端socket.io连接通信地址
        try {
            if(socket !=null && socket.connected()){
                log.info("已连接......");
                return;
            }
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            options.reconnectionAttempts = 2;
            // 失败重连的时间间隔
            options.reconnectionDelay = 1000;
            // 连接超时时间(ms)
            options.timeout = 1000;
            // userId: 唯一标识 传给服务端存储
            socket = IO.socket(url + "?userID=" + userID, options);
            if(socket==null){
                return;
            }
            socket.on(Socket.EVENT_CONNECT, args1 -> socket.emit("login",userID));
            socket.connect();
            socket.on("pc-close",new CloseScreenListener());
            socket.on("broad-message",new BroadcastListener());
            Thread.sleep(1000);
            if(!socket.connected()){
                socket =null ;
                log.info("客户端未连接成功");
            }else{
                log.info("客户端连接成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            socket=null;
            log.error("客户端连接失败",e);
        }
    }

}
