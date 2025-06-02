package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.ToDoListVO;
import com.app.personalbuddyback.repository.CalendarDAO;
import com.app.personalbuddyback.repository.TodoListDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class TodoListServiceImpl implements TodoListService {

    private final TodoListDAO todoListDAO;

    // 투두리스트 등록
    @Override
    public Long registerTodoList(ToDoListVO toDoListVO) {

        return todoListDAO.saveTodoList(toDoListVO);
    }

    // 투두리스트 전체 조회
    @Override
    public List<ToDoListVO> getTodoLists(Long calendarId) {
        return todoListDAO.findAllTodoListByCalendarId(calendarId);
    }



    @Override
    public void modifyTodoList(ToDoListVO toDoListVO) {
        todoListDAO.updateTodoList(toDoListVO);
    }

    @Override
    public void deleteTodoList(Long todoListId) {
        todoListDAO.deleteTodoList(todoListId);
    }
}
