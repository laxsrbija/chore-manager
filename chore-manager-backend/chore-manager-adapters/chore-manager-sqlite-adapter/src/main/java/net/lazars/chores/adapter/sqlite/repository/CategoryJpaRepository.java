package net.lazars.chores.adapter.sqlite.repository;

import net.lazars.chores.adapter.sqlite.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, String> {}