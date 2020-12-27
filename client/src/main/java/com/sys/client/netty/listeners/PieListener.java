package com.sys.client.netty.listeners;

import com.sys.client.executors.ExecutorUtils;
import com.sys.client.M;
import com.sys.client.webcam.MyWebcam;
import io.socket.emitter.Emitter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PieListener implements Emitter.Listener {
    // data:image/jpg;base64,
    //接受到监控指令
    @Override
    public void call(Object... objects) {
        String s = String.valueOf(objects[0]);
        if("close".equals(s)){
            M.IS_TAKE_PHOTO = false ;
        }
        if("start".equals(s)){
            M.IS_TAKE_PHOTO = true ;
        }
        if("start_warn".equals(s)){
            M.IS_WARNING = true ;
        }
        if("close_warn".equals(s)){
            M.IS_WARNING = false ;
        }
    }
}
