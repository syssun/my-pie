package com.sys.server.socketio;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


@Component
public class MessageEventHandler {
    public static SocketIOServer socketIoServer;
    public static ArrayList<UUID> listClient = new ArrayList<>();
    @Autowired
    public MessageEventHandler(SocketIOServer socketIoServer) {
        MessageEventHandler.socketIoServer = socketIoServer;
    }
    @OnConnect
    public void onConnect(SocketIOClient client) {
        listClient.add(client.getSessionId());
        System.err.println(listClient.size());
        System.out.println("客户端:" + client.getSessionId() + "已连接");
        client.sendEvent("webcam","打开摄像头并拍照");
    }
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        System.out.println("客户端:" + client.getSessionId() + "断开连接");
        listClient.remove(client.getSessionId());
    }
    @OnEvent(value = "message") //value是监听事件的名称
    public void onEvent(SocketIOClient client, AckRequest request, Object data) {
        System.out.println(" client messageevent 发来消息：" + data.toString());
        //socketIoServer.getClient(client.getSessionId()).sendEvent("messageevent", "back data"+new Date().getTime());
    }
    @OnEvent(value = "users") //value是监听事件的名称
    public void users(SocketIOClient client, AckRequest request, Object data) {
        System.out.println(" client users 发来消息：" + data.toString());
        socketIoServer.getClient(client.getSessionId()).sendEvent("users", "server回复：已收到您的请求1！");
    }
    @OnEvent(value = "webcam") //value是监听事件的名称
    public void webcam(SocketIOClient client, AckRequest request, Object data) {
        System.out.println(" client webcam 发来消息：" + data.toString());
    }
    public static void sendData(Object data) {   //这里就是向客户端推消息了
        for (UUID clientId : listClient) {
            if (socketIoServer.getClient(clientId) == null) continue;
            socketIoServer.getClient(clientId).sendEvent("messageevent", data);
        }
    }
}
