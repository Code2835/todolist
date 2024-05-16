package com.bcs.todolist;

import com.bcs.todolist.common.FileProcessorService;
import com.bcs.todolist.role.Role;
import com.bcs.todolist.role.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);

		RoleService roleService = new RoleService(new FileProcessorService(new ObjectMapper()));
//		List<Role> roles = roleService.getAllRoles();
//		System.out.println(roles);
//
//		Role role = roleService.getRoleById(1);
//		System.out.println(role);
//
//		Role role2 = new Role(3, "moderator");
//		roleService.saveRole(role2);

		roleService.deleteRole(3);
	}

}
