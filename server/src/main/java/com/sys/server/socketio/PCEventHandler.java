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
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName PCEventHandler
 * @Author sys
 * @Date 2020/12/15 18:35
 * @Description //TODO
 * @Version
 **/
@Slf4j
@Component
public class PCEventHandler {
    @OnEvent(value = "pc-close") //value是监听事件的名称
    public void close(SocketIOClient client, AckRequest request, Object data) {
        log.info("服务端接收到指令：pc-close：{}-",data.toString());
        //除了自己不发
        Map<String,SocketIOClient> m = PoolsClient.getUsers();
        log.info("服务端接收到指令：m：{}-",m.toString());
        for(Map.Entry<String,SocketIOClient> e:m.entrySet()){
            if(e.getValue().getSessionId().toString().equals(client.getSessionId().toString())){
                continue;
            }
            log.info("服务端发送指令：pc-close ：{}-",e.getKey());
            e.getValue().sendEvent("pc-close","1");
        }
    }
}
