package com.jan.todoapp.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public TodoItem create(@RequestBody TodoItem todoItem) {
        return todoService.createOrUpdate(todoItem);
    }

    @GetMapping("/{id}")
    public TodoItem read(@PathVariable Integer id) {
        return todoService.read(id);
    }


    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.PUT})
    public TodoItem update(@PathVariable Integer id, @RequestBody TodoItem todoItem) {
        todoItem.setId(id);
        return todoService.createOrUpdate(todoItem);
    }

    @DeleteMapping("/{id}")
    public TodoItem delete(@PathVariable Integer id) {
        return todoService.delete(id);
    }
}
