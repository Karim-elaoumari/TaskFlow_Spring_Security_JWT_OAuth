package com.Taskflow.security.controllers;

import com.Taskflow.security.SecurityExceptionsHandlers.exception.handlers.response.ResponseMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/main")
@PreAuthorize(value = "(hasRole('USER') or hasRole('ADMIN')) and hasAuthority('READ_USER')")
public class MainController {
    @GetMapping
    public ResponseEntity main() {
        return ResponseMessage.ok(null,"Welcome to TaskFlow API");
    }
}
