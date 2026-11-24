package net.lazars.chores.adapter.sqlite.repository;

import net.lazars.chores.adapter.sqlite.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {}