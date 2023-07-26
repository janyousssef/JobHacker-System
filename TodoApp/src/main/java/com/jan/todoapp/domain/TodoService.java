package com.jan.todoapp.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoItem createOrUpdate(TodoItem todoItem) {
        return todoRepository.save(todoItem);
    }
    public TodoItem read(int id) {
        return todoRepository.findById(id).orElseThrow();
    }
    public TodoItem delete(int id) {
        TodoItem todoItem = todoRepository.findById(id).orElseThrow();
        todoRepository.delete(todoItem);
        return todoItem;
    }

}
