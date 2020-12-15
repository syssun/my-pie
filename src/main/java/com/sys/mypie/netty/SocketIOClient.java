package com.sys.mypie.netty;

import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
    static Socket socket  ;
    private static long userid =  new Date().getTime();
    public static void clientConnect() {
        // 服务端socket.io连接通信地址
        String url = "http://127.0.0.1:8888";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            options.reconnectionAttempts = 2;
            // 失败重连的时间间隔
            options.reconnectionDelay = 1000;
            // 连接超时时间(ms)
            options.timeout = 500;
            // userId: 唯一标识 传给服务端存储
            socket = IO.socket(url + "?userId="+userid, options);
            socket.on(Socket.EVENT_CONNECT, args1 -> socket.send("hello..."));
            // 自定义事件`connected` -> 接收服务端成功连接消息
            socket.on("connected", objects -> log.debug("服务端:" + objects[0].toString()));
            // 自定义事件`push_data_event` -> 接收服务端消息
            socket.on("push_data_event", objects -> log.debug("服务端:" + objects[0].toString()));
            // 自定义事件`myBroadcast` -> 接收服务端广播消息
            socket.on("myBroadcast", objects -> log.debug("服务端：" + objects[0].toString()));
            socket.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
