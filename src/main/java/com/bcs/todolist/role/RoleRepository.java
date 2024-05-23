package com.bcs.todolist.role;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE role SET name = ?2 WHERE id = ?1")
    void update(Integer id, String name);
}
