package com.sys.client.common;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.util.HashMap;

public class BaiduFaceUtil {

    private static final String APP_ID = "23124400";
    private static final String API_KEY = "nSF1xW1XYScn7PI83V3OBl9N";
    private static final String SECRET_KEY = "hMlxRcVqRkQsZB2p3m53LR7tzHK6SnFp";
    private static AipFace client = new AipFace(APP_ID,API_KEY,SECRET_KEY);

    public static JSONObject face(String base64){
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        JSONObject js = sample(client,base64);
        return js ;
    }

    private static JSONObject sample(AipFace client,String image) {
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("face_field", "age");
        options.put("max_face_num", "1");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");
        String imageType = "BASE64";
        // 人脸检测
        //JSONObject res = client.detect(image, imageType, options);
       // System.out.println(res.toString());
        // 人脸搜索
        JSONObject res = client.search(image, imageType,"yemast", options);
        System.out.println(res.toString());
        return res ;
    }



}
