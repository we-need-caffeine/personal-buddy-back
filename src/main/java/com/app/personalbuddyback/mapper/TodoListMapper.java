package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.ToDoListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoListMapper {

    // 투두리스트 할일 등록
    public void insertTodoList(ToDoListVO toDoListVO);

    // 투두리스트 전체 조회
    public List<ToDoListVO> selectAllTodoListsByCalendarId(Long calendarId);

    // 투두리스트 수정
    public void updateTodoList(ToDoListVO toDoListVO);

    // 투두리스트 단일 삭제
    public void deleteTodoList(Long todoListId);

    // 투두리스트 전체 삭제
    public void deleteAllTodoListsByCalendarId(Long calendarId);

}
