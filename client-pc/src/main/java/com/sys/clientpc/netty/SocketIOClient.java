package com.sys.clientpc.netty;

import com.sys.clientpc.netty.listeners.CloseScreenListener;
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

    public static Socket socket;
    private static String userID = "PC-"+(new Date().getTime());

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
            socket.on("pc-close", new CloseScreenListener());
            socket.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
