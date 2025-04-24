package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ToDoListVO {
    private Long id;
    private String todoListContent;
    private Integer todoListIsCompleted;
}
