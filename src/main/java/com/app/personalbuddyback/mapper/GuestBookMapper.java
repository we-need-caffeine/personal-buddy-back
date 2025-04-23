package com.app.personalbuddyback.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GuestBookMapper {
    public String getTime();
}
