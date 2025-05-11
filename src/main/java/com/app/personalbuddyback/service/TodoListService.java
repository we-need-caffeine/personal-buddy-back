package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.ToDoListVO;

import java.util.List;

public interface TodoListService {

    // 투두리스트 할일 등록
    public void registerTodoList(ToDoListVO toDoListVO);

    // 투두리스트 전체 조회
    public List<ToDoListVO> getTodoLists(Long calendarId);

    // 투두리스트 수정
    public void modifyTodoList(ToDoListVO toDoListVO);

    // 투두리스트 삭제
    public void deleteTodoList(Long todoListId);
}
