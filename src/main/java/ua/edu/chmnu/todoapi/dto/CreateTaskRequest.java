package ua.edu.chmnu.todoapi.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskRequest(@NotBlank String title) {}