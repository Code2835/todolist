package com.bcs.todolist.role;

import com.bcs.todolist.person.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

//    public Role() {
//    }

//    public Role(Integer id, String name) {
//        this.id = id;
//        this.name = name;
//    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    @OneToMany(mappedBy = "role")
    private Set<Person> persons;

    @Override
    public String toString() {
        return String.format("%d: %s", id, name);
    }
}
