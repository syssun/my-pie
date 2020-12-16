package com.sys.server.socketio;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@Component
public class SocketEventHandler {
    public static SocketIOServer socketIoServer;
    public static ArrayList<UUID> listClient = new ArrayList<>();
    @Autowired
    public SocketEventHandler(SocketIOServer socketIoServer) {
        SocketEventHandler.socketIoServer = socketIoServer;
    }
    @OnConnect
    public void onConnect(SocketIOClient client) {
        log.info("客户端:" + client.getSessionId() + "连接");
        listClient.add(client.getSessionId());
    }
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        log.info("客户端:" + client.getSessionId() + "断开连接");
        listClient.remove(client.getSessionId());
        PoolsClient.remove(client);
    }
    //接收登录信息
    @OnEvent(value = "login") //
    public void login(SocketIOClient client, AckRequest request, Object data) {
        log.info("客户端登录:" + client.getSessionId() + "-{}-",data.toString());
        PoolsClient.put(data.toString(),client);
    }

}
