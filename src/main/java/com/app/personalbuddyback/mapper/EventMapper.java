package com.app.personalbuddyback.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventMapper {
    public String getTime();
}
