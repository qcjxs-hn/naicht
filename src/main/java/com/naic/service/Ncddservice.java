package com.naic.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.naic.common.Decjm;
import com.naic.common.Locationfw;
import com.naic.entity.*;
import com.naic.mapper.Ncddmapper;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
@EnableConfigurationProperties(Locationfw.class)
public class Ncddservice {

    @Resource
    private Ncddmapper ncddmapper;

    @Resource
    private Ncservice ncservice;

    @Resource
    private Ncggservice ncggservice;

    @Resource
    private Yhqservice yhqservice;

    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;
    private int ddjg;
    private int scjg;
    private String[] jlsz;


    //创建订单
    @SneakyThrows
    public DdFhsz cjdd(Ncdd ncdd){
        ddjg=0;
        scjg=0;
        Date date=new Date();
        String createid=date.getTime()+"";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ncdd.setCreateid(createid);
        ncdd.setCreatedate(java.sql.Date.valueOf(sdf.format(date)));

//        System.out.println(ncdd.getShoppingcar());
        List<Buytea> buyteaList = JSON.parseObject(ncdd.getShoppingcar(), new TypeReference<List<Buytea>>() {});
        for (int i = 0; i < buyteaList.size(); i++) {
           Ncpljl nc= ncservice.selbyname(buyteaList.get(i).getName());
           Ncgg ncgg= ncggservice.getncgg(nc.getCreateid());
            int jljg=0;
            int bxjg=0;
            System.out.println(buyteaList.get(i).getJl());
           if(buyteaList.get(i).getJl().size()!=0) {
                jljg = jsjljg(buyteaList.get(i).getJl(), ncgg.getJl());
           }
//            System.out.println("jljg"+jljg+"");
            if(buyteaList.get(i).getBx()!=null) {
                 bxjg= jsbxjg(buyteaList.get(i).getBx(), ncgg.getBx());
            }
           //计算总价
            ddjg+=nc.getNcjg()+jljg+bxjg;
            //计算上传总价
            scjg+=buyteaList.get(i).getNcjg();
            System.out.println(ddjg+":"+scjg);
        }
        DdFhsz ddFhsz=new DdFhsz();
        ddFhsz.setIc(Decjm.encrypt(createid,Locationfw.jmkey));
        ddFhsz.setIu(Decjm.encrypt(ncdd.getBuyuser(),Locationfw.jmkey));
        if(ncdd.getSfsyyhq().equals("1")){
            String[] yhqsz=ncdd.getSydyhq().split(";");
            int yhqz=0;
            // 使用流处理去除空白元素
            yhqsz = Arrays.stream(yhqsz)
                    .filter(s -> !s.trim().isEmpty())
                    .toArray(String[]::new);
            for (int iyhq = 0; iyhq < yhqsz.length; iyhq++) {
                //将使用的优惠券值求和
                yhqz += yhqservice.getyhq(yhqsz[iyhq]).getYhqje();
                yhqservice.upbyid(yhqsz[iyhq]);
                userService.upbyuserandqid(ncdd.getBuyuser(),yhqsz[iyhq]);
            }
            System.out.println(yhqz);
            if(ddjg-yhqz==scjg-ncdd.getJsje()||(ddjg+1)-yhqz==scjg-ncdd.getJsje()){
                System.out.println("价格相等");
                int cg=ncddmapper.insert(ncdd);

                return  ddFhsz;
            }else {
                System.out.println("价格被修改，账号封禁！");
                userService.upbyusertitle(ncdd.getBuyuser());
                return new DdFhsz();
            }
        }else{
            if(ddjg==scjg||ddjg+1==scjg){
                System.out.println("价格相等");
               // userService.upbyusertitle(ncdd.getBuyuser());
                int cg=ncddmapper.insert(ncdd);
                return ddFhsz;
            }else {
                System.out.println("价格被修改，"+ncdd.getBuyuser()+"账号已被封禁！");
                userService.upbyusertitle(ncdd.getBuyuser());
                return new DdFhsz();
            }
        }
//        System.out.println("Parsed List: " + buyteaList.get(0));
//        System.out.println("Parsed List: " + buyteaList.get(0).getName());
//        System.out.println("Parsed List: " + buyteaList.get(1).getJl());



    }
    //计算加料价格
    public int jsjljg(List<String> jlsz, String ggjl){
        // 将字符串拆分为键值对数组
        String[] KeyValuegg=ggjl.split(";");
        int count = 0;
        // 遍历数组，查找目标,待优化时间复杂度O(n^2)
        for (String gg: KeyValuegg) {
            String[] keyValue = gg.split(":");
            String GgKey = keyValue[0].trim();

            for (int i = 0; i < jlsz.size(); i++) {
                // 如果找到目标，返回其价格
                if (GgKey.equals(jlsz.get(i))) {
                    count+=Integer.parseInt(keyValue[1].trim());
                }
            }

        }

        return count;

    }
    //计算杯型价格
    public int jsbxjg(String ncbx,String ggbx){
        // 将字符串拆分为键值对数组
        String[] KeyValuegg=ggbx.split(";");

        // 遍历数组，查找目标
        for (String gg: KeyValuegg) {
            String[] keyValue = gg.split(":");
            String GgKey = keyValue[0].trim();
            int count = Integer.parseInt(keyValue[1].trim());
            // 如果找到目标,返回其价格
            if(GgKey.equals(ncbx)){
                return count;
            }
        }
        // 如果未找到目标，返回0
        return 0;
    }
//确认订单支付
    @SneakyThrows
    public int qrdd(String ic, String iu) {
//        System.out.println(ic+":"+iu);
        String icjm= Decjm.decrypt(ic,Locationfw.jmkey);
        String iujm=Decjm.decrypt(iu,Locationfw.jmkey);
//        System.out.println(icjm+":"+iujm);
        LambdaQueryWrapper<Ncdd> wrapper= Wrappers.<Ncdd>lambdaQuery();
        wrapper.eq(Ncdd::getCreateid,icjm).eq(Ncdd::getBuyuser,iujm);
        Ncdd ncdd=ncddmapper.selectOne(wrapper);
        if(ncdd.getCreateid()!="") {
            ncdd.setSfzf("1");
            int up = ncddmapper.update(ncdd,wrapper);
            return up;
        }
       else{
           return 0;
        }
    }
//    根据账号查询订单
    public Page<Ncdd> selddbyuser(String u){
//        System.out.println(u);
        LambdaQueryWrapper<Ncdd> wrapper=Wrappers.<Ncdd>lambdaQuery();
        if(u!=""){
            wrapper.eq(Ncdd::getBuyuser,u).orderByDesc(Ncdd::getCreatedate);
            return ncddmapper.selectPage(new Page<>(),wrapper);
        }else{
            return new Page<>();
        }
    }
//==========================后台===========================================
    //发送取餐
    public int fsqc(String i){

        UpdateWrapper<Ncdd> updatawrapper1 = new UpdateWrapper<>();
        updatawrapper1.eq("createid",i).set("sfzf","2");
        return ncddmapper.update(null,updatawrapper1);
    }


    //    根据店铺查询相关订单
    public List<Ncdd> selddbydpmc(String dpmc){
        LambdaQueryWrapper<Ncdd> wrapper=Wrappers.<Ncdd>lambdaQuery();
        if(dpmc.equals("")){
            return null;
        }else{
            wrapper.eq(Ncdd::getDpmc,dpmc);
            return ncddmapper.selectList(wrapper);
        }
    }
//   超级管理员全查
    public List<Ncdd> selddbysupername(String u){
        if(adminService.selu(u)!=null) {
            if (adminService.selu(u).getUserzt().equals("3")) {
                return ncddmapper.selectList(null);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
    //    根据id删除订单
    public int delncdd(String i){
        LambdaQueryWrapper<Ncdd> wrapper=Wrappers.<Ncdd>lambdaQuery();
        wrapper.eq(Ncdd::getCreateid,i);
        return ncddmapper.delete(wrapper);
    }
    //    根据id更新订单
    public int updatancdd(Ncdd ncdd){
        LambdaQueryWrapper<Ncdd> wrapper=Wrappers.<Ncdd>lambdaQuery();
        wrapper.eq(Ncdd::getCreateid,ncdd.getCreateid());
        return ncddmapper.update(ncdd,wrapper);
    }
}
