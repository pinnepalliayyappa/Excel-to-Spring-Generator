package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public List<Person> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Person create(@RequestBody Person entity) {
        return service.save(entity);
    }

    @GetMapping("/{id}")
    public Person getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}