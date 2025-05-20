package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.ToDoListVO;
import com.app.personalbuddyback.mapper.TodoListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoListDAO {

    private final TodoListMapper todoListMapper;

    // 투두리스트 할일 등록
    public Long saveTodoList(ToDoListVO toDoListVO) {
       return todoListMapper.insertTodoList(toDoListVO);
    }

    // 투두리스트 전체 조회
    public List<ToDoListVO> findAllTodoListByCalendarId(Long calendarId) {
        return todoListMapper.selectAllTodoListsByCalendarId(calendarId);
    }

    // 투두리스트 수정
    public void updateTodoList(ToDoListVO toDoListVO) {
        todoListMapper.updateTodoList(toDoListVO);
    }

    // 투두리스트 전체 삭제
    public void deleteAllTodoListsByCalendarId(Long calendarId) {
        todoListMapper.deleteAllTodoListsByCalendarId(calendarId);
    }

    // 투두리스트 삭제
    public void deleteTodoList(Long todoListId)
    {
        todoListMapper.deleteTodoList(todoListId);
    }
}
