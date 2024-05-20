package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.common.Result;
import com.naic.entity.Dpuserdy;
import com.naic.mapper.Cjglmapper;
import com.naic.mapper.Dpuserdympper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DpanduserdyService {
    @Resource
    private Dpuserdympper dpuserdymapper;

    @Resource
    private AdminService adminService;

//    ================超级管理员==========================
// 根据管理员名字查询对应
    public Result<?> selbyuser(@RequestParam String u){
        if(adminService.selus(u)!=null){
            if(adminService.selus(u).getUserzt().equals("1")){
                LambdaQueryWrapper<Dpuserdy> wrapper=Wrappers.<Dpuserdy>lambdaQuery();
                wrapper.eq(Dpuserdy::getUser,u);
                Dpuserdy dpuserdy=dpuserdymapper.selectOne(wrapper);
                if(dpuserdy==null){
                    return Result.error("-2","没有绑定任何店铺！");
                }else{
                    return Result.success(dpuserdy);
                }

            }else{
                return Result.error("-1","无权限！");
            }
        }else {
            return Result.error("-1","无权限！");
        }
    }
    public Dpuserdy seluserbydpmc(String d) {
        LambdaQueryWrapper<Dpuserdy> wrapper=Wrappers.<Dpuserdy>lambdaQuery();
        wrapper.eq(Dpuserdy::getDpmc,d);
        Dpuserdy dpuserdy=dpuserdymapper.selectOne(wrapper);
        if(dpuserdy==null){
            return null;
        }else{
            return dpuserdy;
        }
    }
//超级管理员全查店铺对应
    public List<Dpuserdy> selall(@RequestParam String u){
//        验证是否为超级管理员
        if(adminService.selu(u)!=null) {
            if (adminService.selu(u).getUserzt().equals("3")) {
                return dpuserdymapper.selectList(null);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    //    修改店铺对应信息
    public int upstoredy(Dpuserdy dpuserdy){
        LambdaQueryWrapper<Dpuserdy> wrapper= Wrappers.<Dpuserdy>lambdaQuery();
        wrapper.eq(Dpuserdy::getCreateid,dpuserdy.getCreateid());
        return dpuserdymapper.update(dpuserdy,wrapper);
    }
    //    根据id删除店铺对应
    public int delstoredy(String i){
        LambdaQueryWrapper<Dpuserdy> wrapper=Wrappers.<Dpuserdy>lambdaQuery();
        wrapper.eq(Dpuserdy::getCreateid,i);
        return dpuserdymapper.delete(wrapper);
    }
    //    新增店铺对应
    public int addstoredy(Dpuserdy dpuserdy){
        Date date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LambdaQueryWrapper<Dpuserdy> wrapper=Wrappers.<Dpuserdy>lambdaQuery();
        wrapper.eq(Dpuserdy::getDpmc,dpuserdy.getDpmc());
        if(dpuserdymapper.selectOne(wrapper)==null){
            dpuserdy.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));
            dpuserdy.setCreateid(date.getTime()+"");
            dpuserdy.setDpzt("0");
            return dpuserdymapper.insert(dpuserdy);
        }else{
            return 0;
        }
    }


}
