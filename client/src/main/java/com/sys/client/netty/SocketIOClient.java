package com.sys.client.netty;

import com.sys.client.netty.listeners.BroadcastListener;
import com.sys.client.netty.listeners.ConnectedListener;
import com.sys.client.netty.listeners.MessageListener;
import com.sys.client.netty.listeners.WebCamListener;
import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
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
@Order(99999)
public class SocketIOClient {
    @Value("${netty.server.url}")
    private String url;

    public volatile Socket socket;
    private static String userID = "PIE-001";

    @PostConstruct
    public void clientConnect() {
        // 服务端socket.io连接通信地址
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            options.reconnectionAttempts = 2;
            // 失败重连的时间间隔
            options.reconnectionDelay = 1000;
            // 连接超时时间(ms)
            options.timeout = 1000;
            // userId: 唯一标识 传给服务端存储
            socket = IO.socket(url + "?userID=" + userID, options);
            socket.on(Socket.EVENT_CONNECT, args1 -> socket.send("hello..."));
            // 自定义事件`connected` -> 接收服务端成功连接消息
            socket.on("connected", new ConnectedListener());
            // 自定义事件`push_data_event` -> 接收服务端消息
            socket.on("message", new MessageListener());
            // 自定义事件`myBroadcast` -> 接收服务端广播消息
            socket.on("broadcast", new BroadcastListener());
            socket.on("webcam", new WebCamListener());
            socket.connect();
            Thread.sleep(1000);
            if(!socket.connected()){
                socket =null ;
                log.info("客户端未连接成功");
            }else{
                log.info("客户端连接成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //客户端重连
    @PostConstruct
    public void reConnect(){
        new Thread(() -> {
            try {
                while(true){
                    if(socket==null || !socket.connected()){
                        log.info("正在重新连接");
                        clientConnect();
                    }else{
                        log.info("已连接");
                        Thread.sleep(10000);
                    }
                    Thread.sleep(3000);
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }).start();

    }


}
