package ua.edu.chmnu.todoapi;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(3);

    public TaskService() {
        tasks.add(new Task(1L, "Task 1", false));
        tasks.add(new Task(2L, "Task 2", true));
    }

    public List<Task> findAll() {
        return tasks;
    }

    public Task findById(Long id) {
        return tasks.stream()
                .filter(task -> task.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Task create(CreateTaskRequest request) {
        Task created = new Task(nextId.getAndIncrement(), request.title(), false);
        tasks.add(created);
        return created;
    }

    public void delete(Long id) {
        if (tasks.removeIf(task -> task.id().equals(id))) {
            return;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public void update(Long id, UpdateTaskRequest request) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.id().equals(id)) {
                Task updated = new Task(task.id(), request.title(), request.done());
                tasks.set(i, updated);
                return;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
