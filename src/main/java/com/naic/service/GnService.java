package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.entity.Gn;
import com.naic.entity.Yhq;
import com.naic.entity.Yhzyl;
import com.naic.mapper.Gnmapper;
import com.naic.mapper.Yhzylmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class GnService {
    @Resource
    private Gnmapper gnmapper;

    @Resource
    private Yhzylmapper yhzylmapper;

    //    查询功能栏所有
    public List<?> getgn(){
        return gnmapper.selectList(null);
    }
//   查询我的资源栏所有
    public List<?> getzyl(){
        return yhzylmapper.selectList(null);
    }
//==================后台===========================

    //    新增功能
    public int addgn(Gn gn){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LambdaQueryWrapper<Gn> wrapper= Wrappers.<Gn>lambdaQuery();
        wrapper.eq(Gn::getTitle,gn.getTitle());
        if(gnmapper.selectOne(wrapper)==null){
            gn.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));
            gn.setCreateid(date.getTime()+"");
            return gnmapper.insert(gn);
        }else{
            return 0;
        }
    }
    //    修改功能信息
    public int upgn(Gn gn){
        LambdaQueryWrapper<Gn> wrapper= Wrappers.<Gn>lambdaQuery();
        wrapper.eq(Gn::getTitle,gn.getTitle());
        return gnmapper.update(gn,wrapper);
    }
    //    根据id删除对应功能
    public int delgn(String i){
        LambdaQueryWrapper<Gn> wrapper=Wrappers.<Gn>lambdaQuery();
        wrapper.eq(Gn::getCreateid,i);
        return gnmapper.delete(wrapper);
    }
    //    新增我的资源栏
    public int addmygn(Yhzyl yhzyl){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LambdaQueryWrapper<Yhzyl> wrapper= Wrappers.<Yhzyl>lambdaQuery();
        wrapper.eq(Yhzyl::getSm,yhzyl.getSm());
        if(yhzylmapper.selectOne(wrapper)==null){
            yhzyl.setBt("0");
            yhzyl.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));
            yhzyl.setCreateid(date.getTime()+"");
            return yhzylmapper.insert(yhzyl);
        }else{
            return 0;
        }
    }
    //    修改我的资源栏信息
    public int upmygn(Yhzyl yhzyl){
        LambdaQueryWrapper<Yhzyl> wrapper= Wrappers.<Yhzyl>lambdaQuery();
        wrapper.eq(Yhzyl::getSm,yhzyl.getSm());
        return yhzylmapper.update(yhzyl,wrapper);
    }
    //    根据id删除对应我的资源栏
    public int delmygn(String i){
        LambdaQueryWrapper<Yhzyl> wrapper=Wrappers.<Yhzyl>lambdaQuery();
        wrapper.eq(Yhzyl::getCreateid,i);
        return yhzylmapper.delete(wrapper);
    }
}
