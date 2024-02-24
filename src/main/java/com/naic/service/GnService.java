package com.naic.service;

import com.naic.mapper.Gnmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GnService {
    @Resource
    private Gnmapper gnmapper;

    public List<?> getgn(){
        return gnmapper.selectList(null);
    }
}
