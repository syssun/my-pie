package com.sys.client.timer;

import com.sys.client.M;
import com.sys.client.common.BaiduFaceUtil;
import com.sys.client.common.BaiduSpeehUtil;
import com.sys.client.music.PlayMusic;
import com.sys.client.netty.SocketIOClient;
import com.sys.client.utils.StringUtils;
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
                                    String wj = StringUtils.GAO_JIN ;
                                    int hashcode = Math.abs(wj.hashCode());
                                    String path = "/pie/mp3/"+hashcode+".mp3" ;
                                    BaiduSpeehUtil.speech(wj);
                                    PlayMusic.playMusic(path,1);
                                    if(s !=null){
                                        socketIOClient.getSocket().emit("webcam",s,"gaojin"); //发送给服务端
                                    }
                                }
                            }else{
                                switch (user_id){
                                    case "sunys":
                                        String str = "欢迎孙大帅 ！" ;
                                        int hashcode = Math.abs(str.hashCode());
                                        String path = "/pie/mp3/"+hashcode+".mp3" ;
                                        BaiduSpeehUtil.speech(str);
                                        PlayMusic.playMusic(path,1);
                                        break;
                                    case "qianl":
                                        String str2 = "欢迎钱乐 ！" ;
                                        int hashcode2 = Math.abs(str2.hashCode());
                                        String path2 = "/pie/mp3/"+hashcode2+".mp3" ;
                                        BaiduSpeehUtil.speech(str2);
                                        PlayMusic.playMusic(path2,1);
                                        break;
                                }
                            }
                        }

                    }else if(error_code==222202){  //没有检测到人脸

                    }

                }
                if(s !=null){
                    socketIOClient.getSocket().emit("webcam",s,"live"); //发送给服务端
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
