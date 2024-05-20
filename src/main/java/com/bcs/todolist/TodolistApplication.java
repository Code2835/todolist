package com.bcs.todolist;

import com.bcs.todolist.common.FileProcessorService;
import com.bcs.todolist.role.Role;
import com.bcs.todolist.role.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.List;

@SpringBootApplication
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);

//		RoleService roleService = new RoleService(new FileProcessorService(new ObjectMapper()));
//		List<Role> roles = roleService.getAllRoles();
//		System.out.println(roles);
//
//		Role role = roleService.getRoleById(1);
//		System.out.println(role);
//
//		Role role2 = new Role(3, "moderator");
//		roleService.saveRole(role2);

//		roleService.deleteRole(3);


		String JDBC_URL="jdbc:postgresql://localhost:5432/todolist";

		// try-with-resources, opens and closes resources
		try (Connection conn = DriverManager.getConnection(JDBC_URL, "postgres", "admin");
			 Statement stmt = conn.createStatement();
			 // '1' OR '1' = '1'; DROP DATABASE
			 PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM role WHERE id = ?");
		) {
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS role (id SERIAL PRIMARY KEY,	name VARCHAR(255))");
			System.out.println("Role table created");

			stmt.executeUpdate("INSERT INTO role (name) VALUES ('admin')");

			ResultSet rs = stmt.executeQuery("SELECT * FROM role");
			while(rs.next()) {
				System.out.println(rs.getLong("id") + ": " + rs.getString("name"));
			}

			preparedStmt.setInt(1, 2);
			ResultSet preparedRs = preparedStmt.executeQuery();
			while(preparedRs.next()) {
				System.out.println("Prepared stmt - " + preparedRs.getLong("id") + ": " + preparedRs.getString("name"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}


	}

}
