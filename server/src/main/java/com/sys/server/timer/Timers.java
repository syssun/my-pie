package com.sys.server.timer;

import com.corundumstudio.socketio.SocketIOClient;
import com.sys.server.socketio.PoolsClient;
import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @ClassName timer
 * @Author sys
 * @Date 2020/12/16 11:23
 * @Description //TODO
 * @Version
 **/
@Component
public class Timers {

    //五秒发送在线用户信息
    @Scheduled(cron = "0/5 * * * * *")
    public void getUser(){
        List<String> m = PoolsClient.getUsersKey();
        JSONObject j = new JSONObject();
        try {
            j.put("all-users",m);
        }catch (Exception e){}
        for(Map.Entry<String,SocketIOClient> e:PoolsClient.getUsers().entrySet()){
            e.getValue().sendEvent("online-users", j.toString());
        }
    }

}
