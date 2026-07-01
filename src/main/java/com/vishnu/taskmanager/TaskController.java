package com.vishnu.taskmanager;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>(List.of(
            new Task(1, "Buy groceries", false),
            new Task(2, "Read a book", false),
            new Task(3, "Walk the dog", true)
    ));

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public Task addTask(@RequestBody Task task) {
        task.setId(tasks.size() + 1);
        tasks.add(task);
        return task;
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        tasks.removeIf(t -> t.getId() == id);
        return "Task " + id + " deleted";
    }
}