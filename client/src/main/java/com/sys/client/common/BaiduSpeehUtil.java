package com.sys.client.common;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class BaiduSpeehUtil {
    private static final String APP_ID = "18275842";
    private static final String API_KEY = "4dg3jgezMgXAHRCs38cuWxsu";
    private static final String SECRET_KEY = "sh1Y5oHBdAMHT90wmqrhIGnZKVcGaFFz";


    public static void speech(String str) {
        int hashcode = Math.abs(str.hashCode());
        String path = "/pie/mp3/"+hashcode+".mp3" ;
        File f = new File(path);
        if(f.exists()){
            return;
        }
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 调用接口
        TtsResponse res = client.synthesis(str, "zh", 1, null);
        byte[] data = res.getData();
        if (data != null) {
            try {
                f.getParentFile().mkdirs();
                Util.writeBytesToFileSystem(data, path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
