package net.lazars.chores.adapter.sqlite.repository;

import net.lazars.chores.adapter.sqlite.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<ItemEntity, String> {}
