package net.lazars.chores.adapter.sqlite.repository;

import net.lazars.chores.adapter.sqlite.entity.HouseholdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdJpaRepository extends JpaRepository<HouseholdEntity, String> {}
