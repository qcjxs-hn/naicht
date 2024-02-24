package com.naic.service;

import com.naic.entity.Qyl;
import com.naic.mapper.Qylmapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QylService {
    @Resource
    Qylmapper qylmapper;

    public List<Qyl> getqyl(){
        return qylmapper.selectList(null);
    }

}
