package com.example.todolist;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@SpringBootApplication
@Controller
public class TodoApplication {

    private final List<String> todos = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("todos", todos);
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String task) {
        if (!task.trim().isEmpty()) {
            todos.add(task);
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{index}")
    public String deleteTask(@PathVariable int index) {
        if (index >= 0 && index < todos.size()) {
            todos.remove(index);
        }
        return "redirect:/";
    }

    @GetMapping("/clear")
    public String clearTasks() {
        todos.clear();
        return "redirect:/";
    }
}