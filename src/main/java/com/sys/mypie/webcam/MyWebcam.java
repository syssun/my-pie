package com.sys.mypie.webcam;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamUtils;
import com.github.sarxos.webcam.util.ImageUtils;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

/**
 * @ClassName MyWebcam
 * @Author sys
 * @Date 2020/12/15 16:07
 * @Description //TODO
 * @Version
 **/
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
    public ByteBuffer getImageBytes(Webcam webcam){
        return  webcam.getImageBytes();
    }


}
