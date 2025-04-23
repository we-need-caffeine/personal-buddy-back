package com.app.personalbuddyback.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalendarMapper {
    public String getTime();
}
