package com.naic.common;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.naic.entity.User;
import com.naic.mapper.Usermapper;
import com.naic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;


@Component
//定时初始化
public class Dscsq {

    @Autowired
    private Usermapper usermapper;

    // 每天凌晨零点执行一次
    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduleUpdateTask() {
        // 创建定时器
        Timer timer = new Timer();

        // 创建定时任务
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 执行更新操作
                updateAllUserQdcxToZero();
                System.out.println("Updated userqdcx to zero at " + LocalDateTime.now());
            }
        };

        // 计算执行时间，一天的毫秒数
        long oneDayInMillis =1000;

        // 在一天后执行定时任务，然后每隔一天重复执行
        timer.schedule(task, oneDayInMillis);
    }

    // 更新所有用户的userqdcx为0
    public void updateAllUserQdcxToZero() {
        // 构建更新条件
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        // 设置更新字段和更新值
        updateWrapper.set(User::getUserqdcx, 0);

        // 执行更新操作
        int updatedRows = usermapper.update(null, updateWrapper);
        System.out.println("Updated " + updatedRows + " rows of userqdcx to zero.");
    }

}
