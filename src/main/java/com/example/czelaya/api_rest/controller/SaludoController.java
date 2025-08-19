package com.example.czelaya.api_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/micontroller")
public class SaludoController {

    public record NameDto(String name) {}

    @GetMapping("/get-names")
    public ResponseEntity<List<NameDto>> getNames() {
        var names = List.of("Cristian", "Juan", "Maria");
        var response = names.stream().map(NameDto::new).toList();
        return ResponseEntity.ok(response);
    }
}
