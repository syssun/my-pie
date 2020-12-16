package com.sys.server.socketio;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.stereotype.Component;

/**
 * @ClassName PieEventHandler
 * @Author sys
 * @Date 2020/12/16 10:52
 * @Description //TODO
 * @Version
 **/
@Component
public class PieEventHandler {


    @OnEvent(value = "webcam") //value是监听事件的名称
    public void webcam(SocketIOClient client, AckRequest request, Object data) {
        System.out.println(" client webcam 发来消息：" + data.toString());
    }
}
