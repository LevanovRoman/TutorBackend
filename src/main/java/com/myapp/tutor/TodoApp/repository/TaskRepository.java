package com.myapp.tutor.TodoApp.repository;

import com.myapp.tutor.TodoApp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
