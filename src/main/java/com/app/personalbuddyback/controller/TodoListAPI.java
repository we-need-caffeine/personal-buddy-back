package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.ToDoListVO;
import com.app.personalbuddyback.service.TodoListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo-lists/api/*")
@Slf4j
public class TodoListAPI {

    private final TodoListService todoListService;

    @Operation(summary = "투두리스트 할일 등록", description = "투두리스트에 할일을 등록할 수 있는 API")
    @PostMapping("register")
    public void registerTodoList(@RequestBody ToDoListVO toDoListVO) {
        todoListService.registerTodoList(toDoListVO);
    }

    @Operation(summary = "투두리스트 전체 조회", description = "투두리스트 전체 할일 목록을 조회하는 API")
    @Parameter(
            name = "calendarId",
            description = "일정 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("{calendarId}")
    public List<ToDoListVO> getTodoLists(@PathVariable Long calendarId) {
        return todoListService.getTodoLists(calendarId);
    }

    @Operation(summary = "투두리스트 수정", description = "투두리스트 항목을 수정하는 API")
    @PutMapping("")
    public void modifyTodoList(@RequestBody ToDoListVO toDoListVO) {
        todoListService.modifyTodoList(toDoListVO);
    }


    @Operation(summary = "투두리스트 삭제", description = "투두리스트 항목을 삭제하는 API")
    @DeleteMapping("{todoListId}")
    public void deleteTodoList(@PathVariable Long todoListId) {
        todoListService.deleteTodoList(todoListId);
    }

}
