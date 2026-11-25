package net.lazars.chores.adapter.sqlite.repository;

import net.lazars.chores.adapter.sqlite.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, String> {}
