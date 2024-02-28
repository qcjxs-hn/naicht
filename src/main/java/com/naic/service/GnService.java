package com.naic.service;

import com.naic.mapper.Gnmapper;
import com.naic.mapper.Yhzylmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

}
