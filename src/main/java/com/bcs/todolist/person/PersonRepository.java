package com.bcs.todolist.person;

import com.bcs.todolist.role.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Transactional
    @Modifying
    @Query("UPDATE person SET firstName = ?2, lastName = ?3, role = ?4 WHERE id = ?1")
    void update(Integer id, String firstName, String lastName, Role role);

    Optional<Person> findByUsername(String username);
}
