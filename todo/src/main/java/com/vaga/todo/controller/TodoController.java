package com.vaga.todo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaga.todo.dto.TodoDto;
import com.vaga.todo.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    
    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody @Valid TodoDto todoDto, JwtAuthenticationToken token){
        TodoDto create = todoService.createTodo(todoDto, token);
        return new ResponseEntity<TodoDto>(create, HttpStatus.CREATED);
    }

    @PutMapping("/{idTodo}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable UUID idTodo, @RequestBody @Valid TodoDto todoDto, JwtAuthenticationToken token){
        TodoDto update = todoService.updateTodo(idTodo, todoDto, token);
        return ResponseEntity.ok(update);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> listTodoUser(JwtAuthenticationToken token){
        List<TodoDto> list = todoService.listTodoUser(token);
        return ResponseEntity.ok(list);
    }   

    @DeleteMapping("/{idTodo}")
    public ResponseEntity deleteTodoUser(@PathVariable UUID idTodo, JwtAuthenticationToken token){
        todoService.deleteTodoUser(idTodo, token);
        return ResponseEntity.noContent().build();
    }
}
