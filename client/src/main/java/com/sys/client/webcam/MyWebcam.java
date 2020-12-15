package com.sys.client.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.nio.ByteBuffer;

/**
 * @ClassName MyWebcam
 * @Author sys
 * @Date 2020/12/15 16:07
 * @Description //TODO
 * @Version
 **/
@Slf4j
public class MyWebcam {
    /**
     * websocket 客户端
     * 图片路径
     * @param fileName
     */
    public void getImage(Webcam webcam,String fileName){
        try {
           webcam.open();
           WebcamUtils.capture(webcam, fileName, ImageUtils.FORMAT_JPG);
        }catch (Exception e){
            if(webcam.isOpen()){
                webcam.close();
            }
        }
    }

    /**
     * websocket 客户端
     * 图片路径
     * @param
     */
    public static String getImageBase64(){
        Webcam webcam = Webcam.getDefault();
        try {
            webcam.open();
            BASE64Encoder encoder = new BASE64Encoder();
            byte[] b = WebcamUtils.getImageBytes(webcam,ImageUtils.FORMAT_JPG);
            String png_base64 =  encoder.encodeBuffer(b).trim();//转换成base64串
            png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
            return png_base64;
        }catch (Exception e){
            if(webcam.isOpen()){
                webcam.close();
            }
        }
        return null;
    }




    public ByteBuffer getImageBytes(Webcam webcam){
        return  webcam.getImageBytes();
    }


}
