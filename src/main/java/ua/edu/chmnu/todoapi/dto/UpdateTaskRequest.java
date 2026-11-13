package ua.edu.chmnu.todoapi.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateTaskRequest(@NotBlank String title, boolean done) {
}
