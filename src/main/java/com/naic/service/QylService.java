package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.entity.Qyl;
import com.naic.entity.Yhzyl;
import com.naic.mapper.Qylmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class QylService {
    @Resource
    Qylmapper qylmapper;
    //首页权益栏
    public List<Qyl> getqyl(){
        return qylmapper.selectList(null);
    }
//=============================后台======================================
    //    新增我的资源栏
    public int addqyl(Qyl qyl){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LambdaQueryWrapper<Qyl> wrapper= Wrappers.<Qyl>lambdaQuery();
        wrapper.eq(Qyl::getQylwz,qyl.getQylwz());
        if(qylmapper.selectOne(wrapper)==null){

            qyl.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));

            return qylmapper.insert(qyl);
        }else{
            return 0;
        }
    }
    //    修改我的资源栏信息
    public int upqyl(Qyl qyl){
        LambdaQueryWrapper<Qyl> wrapper= Wrappers.<Qyl>lambdaQuery();
        wrapper.eq(Qyl::getId,qyl.getId());
        return qylmapper.update(qyl,wrapper);
    }
    //    根据id删除对应我的资源栏
    public int delqyl(String t){
        LambdaQueryWrapper<Qyl> wrapper=Wrappers.<Qyl>lambdaQuery();
        wrapper.eq(Qyl::getQylwz,t);
        return qylmapper.delete(wrapper);
    }

}
