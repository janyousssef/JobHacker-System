package com.jan.todoapp.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public List<TodoItem> readAll() {
        return todoRepository.findAll();
    }

    public TodoItem createOrUpdate(TodoItem todoItem) {
        return todoRepository.save(todoItem);
    }

    public TodoItem read(int id) {
        return todoRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                              "TodoItem with id " + id + "not found"));
    }

    public void delete(int id) {
        todoRepository.deleteById(id);
    }

}
