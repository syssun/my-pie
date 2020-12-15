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
import java.util.UUID;

/**
 * @ClassName PCEventHandler
 * @Author sys
 * @Date 2020/12/15 18:35
 * @Description //TODO
 * @Version
 **/
@Component
public class PCEventHandler {
    public static SocketIOServer socketIoServer;
    @Autowired
    public PCEventHandler(SocketIOServer socketIoServer) {
        PCEventHandler.socketIoServer = socketIoServer;
    }

    @OnEvent(value = "pc-close") //value是监听事件的名称
    public void close(SocketIOClient client, AckRequest request, Object data) {
        client.sendEvent("pc-close","1");
    }

}
