package com.myapp.tutor.repository;

import com.myapp.tutor.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
