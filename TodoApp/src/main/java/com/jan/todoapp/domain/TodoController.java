package com.jan.todoapp.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public List<TodoItem> readAllTodos() {
        return todoService.readAll();
    }

    @PostMapping
    public TodoItem createTodo(@RequestBody TodoItem todoItem) {
        return todoService.createOrUpdate(todoItem);
    }

    @GetMapping("/{id}")
    public TodoItem readTodo(@PathVariable Integer id) {
        return todoService.read(id);
    }


    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public TodoItem updateTodo(@PathVariable Integer id, @RequestBody TodoItem todoItem) {
        todoItem.setId(id);
        return todoService.createOrUpdate(todoItem);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Integer id) {
        todoService.delete(id);
    }
}
