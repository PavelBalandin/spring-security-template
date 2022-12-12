package com.example.controller;

import com.example.dto.UserDTO;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Set<UserDTO>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") Long id, @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.update(id, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

}
