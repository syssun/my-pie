package com.sys.server.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName PieEventHandler
 * @Author sys
 * @Date 2020/12/16 10:52
 * @Description //TODO
 * @Version
 **/
@Slf4j
@Component
public class PieEventHandler {


    @OnEvent(value = "webcam") //value是监听事件的名称
    public void webcam(SocketIOClient client, AckRequest request, Object data) {
        System.out.println(" client webcam 发来消息：" + data.toString());
        //除了自己不发
        Map<String,SocketIOClient> m = PoolsClient.getUsers();
        for(Map.Entry<String,SocketIOClient> e:m.entrySet()){
            if(e.getValue().getSessionId().toString().equals(client.getSessionId().toString())){
                continue;
            }
            e.getValue().sendEvent("pie-res",data.toString()); //发送给安卓客户端
        }
    }



    @OnEvent(value = "pie-ctl") //value是监听事件的名称
    public void pieCtrl(SocketIOClient client, AckRequest request, Object data) {
        log.info("服务端接收到指令：pc-ctl：{}-",data.toString());
        //除了自己不发
        Map<String,SocketIOClient> m = PoolsClient.getUsers();
        for(Map.Entry<String,SocketIOClient> e:m.entrySet()){
            if(e.getValue().getSessionId().toString().equals(client.getSessionId().toString())){
                continue;
            }
            e.getValue().sendEvent("pie-ctl",data.toString());//发送给客户端
        }
    }






}
