package com.example.edu.controller;

import com.example.edu.entity.Authority;
import com.example.edu.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Authority add(@RequestBody Authority authority) {
        return authorityService.add(authority);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Authority> getAll() {
        return authorityService.getAll();
    }

}
