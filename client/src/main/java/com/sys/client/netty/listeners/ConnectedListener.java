package com.sys.client.netty.listeners;

import io.socket.emitter.Emitter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ConnectedListener
 * @Author sys
 * @Date 2020/12/15 17:20
 * @Description //TODO
 * @Version
 **/
@Slf4j
public class ConnectedListener implements Emitter.Listener  {
    @Override
    public void call(Object... objects) {
        log.info("客户端连接。。。。。{}",objects.toString());
    }
}
