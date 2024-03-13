package com.naic.service;

import com.naic.entity.Dpuserdy;
import com.naic.mapper.Cjglmapper;
import com.naic.mapper.Dpuserdympper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DpanduserdyService {
    @Resource
    private Dpuserdympper dpuserdympper;

    @Resource
    private AdminService adminService;

//    ================超级管理员==========================
    public List<Dpuserdy> selall(@RequestParam String u){
//        验证是否为超级管理员
        if(adminService.selu(u)!=null) {
            if (adminService.selu(u).getUserzt().equals("3")) {
                return dpuserdympper.selectList(null);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
