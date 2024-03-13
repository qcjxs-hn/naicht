package com.naic.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.naic.entity.Cjgl;
import com.naic.entity.Lx;
import com.naic.mapper.Cjglmapper;
import com.naic.mapper.Lxmapper;
import com.naic.mapper.Usermapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LxService {
    @Resource
    private Lxmapper lxmapper;

    @Resource
    private Cjglmapper cjglmapper;

    public int fsxx(Lx lx){
        LambdaQueryWrapper<Lx> wrapper = Wrappers.<Lx>lambdaQuery();
        wrapper.like(Lx::getCreateid,lx.getUser());
        List<Lx> lx1=lxmapper.selectList(wrapper);

        ArrayList<Integer> gsList = new ArrayList<>();
        Date date=new Date();
        System.out.println(lx1);
        if(lx1.size()==0){
            //根据管理员回复人数分流
           List<Cjgl> cjgl =cjglmapper.selectList(null);
            LambdaQueryWrapper<Lx> wrapper2 = Wrappers.<Lx>lambdaQuery();
            for (int i = 0; i < cjgl.size(); i++) {
                System.out.println(cjgl.get(i).getUser());
                wrapper2.like(Lx::getCreateid,cjgl.get(i).getUser());
                 gsList.add(Math.toIntExact(lxmapper.selectCount(wrapper2)));
            }
            // 查找数组中的最小值和其下标
            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int i = 0; i < gsList.size(); i++) {
                if (gsList.get(i) < minValue) {
                    minValue = gsList.get(i);
                    minIndex = i;
                }
            }
            // 弹出最小值对应的下标
            if (minIndex != -1) {
                System.out.println("最小值所在的下标为: " + minIndex);
            } else {
                System.out.println("数组为空，无法执行操作。");
            }
            System.out.print( cjgl.get(minIndex).getUser());
            lx.setCreateid(lx.getUser()+cjgl.get(minIndex).getUser());
            lx.setMesid(date.getTime()+"");
            int i =lxmapper.insert(lx);
            return i;
//            cjgl.get(minIndex);
//            lx.setCreateid();
        }else {
            System.out.println(lx);
            lx.setCreateid(lx1.get(0).getCreateid());
            lx.setMesid(date.getTime() + "");
            System.out.println(lx.getContent());
            int i = lxmapper.insert(lx);
            return i;
        }
    }

    public List<?> getmes(String u) {
        LambdaQueryWrapper<Lx> wrapper = Wrappers.<Lx>lambdaQuery();
        wrapper.like(Lx::getCreateid,u);
        List<Lx> lx1=lxmapper.selectList(wrapper);
        return lx1;
    }
}
