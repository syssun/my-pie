package com.sys.server.socketio;

import com.corundumstudio.socketio.SocketIOClient;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName PoolsClient
 * @Author sys
 * @Date 2020/12/16 10:51
 * @Description //TODO
 * @Version
 **/
@Slf4j
public class PoolsClient {
    private static volatile Map<String, SocketIOClient>  POOLS = new ConcurrentHashMap<>();

    //put
    public static void put(String usreid,SocketIOClient client){
        POOLS.put(usreid,client);
        log.info("size:{}-",POOLS.size());
    }
    //remove
    public static void remove(String usreid){
        if(POOLS.containsKey(usreid)){
            POOLS.remove(usreid);
        }
        log.info("size:{}-",POOLS.size());
    }
    public static void remove(SocketIOClient client){
        for(Map.Entry<String,SocketIOClient> e:POOLS.entrySet()){
            if(e.getValue().getSessionId().toString().equals(client.getSessionId().toString())){
                POOLS.remove(e.getKey());
            }
        }
    }
    //get
    public static SocketIOClient get(String usreid){
        if(POOLS.containsKey(usreid)){
            return  POOLS.get(usreid);
        }
        return null;
    }

    //通过client 返回clent
    public static String getUserid(SocketIOClient client){
        for(Map.Entry<String,SocketIOClient> e:POOLS.entrySet()){
            if(e.getValue().getSessionId().toString().equals(client.getSessionId().toString())){
                return e.getKey();
            }
        }
        return "";
    }
    public static Map<String, SocketIOClient> getUsers(){
        return POOLS;
    }
    public static List<String> getUsersKey(){
        List<String> res = new ArrayList<>();
        for(Map.Entry<String,SocketIOClient> e:POOLS.entrySet()){
            res.add(e.getKey());
        }
        return res;
    }
}
