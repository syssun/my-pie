package com.sys.client.utils;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class StringUtils {


    public static String GAO_JIN = "有陌生人进入，请及时确认！" ;
    public static String QL = "老婆大人驾到！";
    public static String WELCOME = "欢迎光临";
    public static String QI_CHUANG = "起床啦！起床啦！";

    private static final String APP_ID = "18275842";
    private static final String API_KEY = "4dg3jgezMgXAHRCs38cuWxsu";
    private static final String SECRET_KEY = "sh1Y5oHBdAMHT90wmqrhIGnZKVcGaFFz";


    @SneakyThrows
    public static void main(String[] args) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        TtsResponse res = client.synthesis("你好百度!", "zh", 1, null);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                File f = new File("/pie/mp3/output.mp3");
                f.mkdirs();
                Util.writeBytesToFileSystem(data, "/pie/mp3/output.mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }

    }
}
