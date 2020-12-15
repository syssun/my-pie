package com.sys.clientpc.controller;

import com.sys.clientpc.netty.SocketIOClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CtrlController {
    @Autowired
    SocketIOClient socketIOClient;
    @RequestMapping("/index")
    public String close(){
        socketIOClient.getSocket().emit("pc-close","");
        return "1";
    }
}
