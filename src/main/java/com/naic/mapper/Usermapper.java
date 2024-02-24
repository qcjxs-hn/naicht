package com.naic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.naic.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Usermapper extends BaseMapper<User> {
}
