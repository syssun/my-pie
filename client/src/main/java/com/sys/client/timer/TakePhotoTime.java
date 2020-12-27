package com.sys.client.timer;

import com.sys.client.M;
import com.sys.client.common.BaiduFaceUtil;
import com.sys.client.music.PlayMusic;
import com.sys.client.netty.SocketIOClient;
import com.sys.client.webcam.MyWebcam;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TakePhotoTime {

    @Autowired
    SocketIOClient socketIOClient;
    //五秒发送在线用户信息
    @Scheduled(cron = "0/3 * * * * *")
    public void tabkePhoto(){
        try {
            if(M.IS_TAKE_PHOTO){
                String s =  MyWebcam.getImageBase64();
                //这里获取了图片 调用百度api 进行人脸识别 并告警
                JSONObject j = BaiduFaceUtil.face(s);
                if(j!=null){
                    String error_msg = j.getString("error_msg");
                    int error_code = j.getInt("error_code");
                    if(error_code==0){
                        //看分数
                        JSONArray arr  = j.getJSONObject("result").getJSONArray("user_list");
                        if(arr !=null && arr.length()>0){
                            JSONObject sc = (JSONObject)arr.get(0);
                            double score = sc.getDouble("score");
                            String user_id = sc.getString("user_id") ;
                            if(score<60){
                                if(M.IS_WARNING) {
                                    PlayMusic.playMusic("D:\\java\\python\\mypython\\test\\gaojin.mp3", 1);
                                }
                            }
                        }

                    }else if(error_code==222202){  //没有检测到人脸

                    }

                }
                if(s !=null){
                    socketIOClient.getSocket().emit("webcam",s); //发送给服务端
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
