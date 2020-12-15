package com.sys.mypie.music;

import com.sys.mypie.executors.ExecutorUtils;
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
    public static void playMusic(String path) {
        File file = null;
        FileInputStream fis = null;
        BufferedInputStream stream = null;
        Player player = null;
        try {
            file = new File(path);
            fis = new FileInputStream(file);
            stream = new BufferedInputStream(fis);
            player = new Player(stream);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
            } catch (Exception e) {

            }

        }
    }


    public static void playMusic(String path,int times) {
        File file = null;
        FileInputStream fis = null;
        BufferedInputStream stream = null;
        Player player = null;
        try {
            file = new File(path);
            fis = new FileInputStream(file);
            stream = new BufferedInputStream(fis);
            player = new Player(stream);

            Player finalPlayer = player;
            ExecutorUtils.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        for(int i=0;i<times;i++) {
                            finalPlayer.play();
                        }
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }








}
