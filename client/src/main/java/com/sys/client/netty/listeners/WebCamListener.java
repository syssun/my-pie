package com.sys.client.netty.listeners;

import com.sys.client.netty.SocketIOClient;
import com.sys.client.webcam.MyWebcam;
import io.socket.emitter.Emitter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName WebCamListener
 * @Author sys
 * @Date 2020/12/15 17:33
 * @Description data:image/jpg;base64,
 * @Version
 **/
@Slf4j
public class WebCamListener implements Emitter.Listener {
    // data:image/jpg;base64,
    @Override
    public void call(Object... objects) {
       String s =  MyWebcam.getImageBase64();
       log.info("base64:{}<<<",s);
       //将图片在发送给服务端
        //SocketIOClient..emit("webcam",s);
    }
}
