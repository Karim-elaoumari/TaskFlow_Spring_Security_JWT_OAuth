package com.Taskflow.security.repositories;

import com.Taskflow.security.models.entities.PrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity, Integer> {
    Optional<PrivilegeEntity> findByName(String name);
}
