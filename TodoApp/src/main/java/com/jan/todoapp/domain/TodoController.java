package com.jan.todoapp.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/{id}")
    public TodoItem read(@PathVariable Integer id) {
        return todoService.read(id);
    }

    @PostMapping
    public TodoItem create(@RequestBody TodoItem todoItem) {
        return todoService.createOrUpdate(todoItem);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public TodoItem update(@PathVariable Integer id, @RequestBody TodoItem todoItem) {
        todoItem.setId(id);
        return todoService.createOrUpdate(todoItem);
    }
}
