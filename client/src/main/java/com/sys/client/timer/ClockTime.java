package com.sys.client.timer;

import com.sys.client.M;
import com.sys.client.executors.ExecutorUtils;
import com.sys.client.music.PlayMusic;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClockTime {

    //八点
    @Scheduled(cron = "0/5 * * * * *")
    public void clock8(){
        ExecutorUtils.submit(new Runnable() {
            int  count = 0 ;
            @Override
            public void run() {
                try {
                    while (true){
                        if(!M.IS_CLOCK){
                            break;
                        }
                        if(count>=3){
                            break;
                        }
                        count = count+1 ;
                        //PlayMusic.playMusic("",1);
                        Thread.sleep(1000*60*5);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

    }
}
