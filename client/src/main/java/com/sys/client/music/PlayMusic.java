package com.sys.client.music;

import com.sys.client.executors.ExecutorUtils;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @ClassName PlayMusic
 * @Author sys
 * @Date 2020/12/14 0:22
 * @Description //TODO
 * @Version
 **/
public class PlayMusic {

    public static void playMusic(String path,int times) {
            ExecutorUtils.submit(() -> {
                try {
                    File file = null;
                    FileInputStream fis = null;
                    BufferedInputStream stream = null;
                    Player player = null;
                    try {
                        file = new File(path);
                        fis = new FileInputStream(file);
                        stream = new BufferedInputStream(fis);
                        player = new Player(stream);
                        for(int i=0;i<times;i++) {
                            player.play();
                            Thread.sleep(5000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        try {
                            if (player != null) {
                                player.close();
                            }
                            if (stream != null) {
                                stream.close();
                            }
                            if (fis != null) {
                                fis.close();
                            }
                        } catch (Exception s) {

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
    }

}
