package com.bcs.todolist.role;

import com.bcs.todolist.role.dto.GetRoleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@SpringBootTest
public class RoleServiceTest {
    @Autowired
    RoleService roleService;

    @MockBean
    private RoleRepository roleRepository;


    @Test
    public void getAllRolesList() {
        // setup
        Role role1 = new Role();
        role1.setId(1);
        role1.setName("fakeRole1");

        Role role2 = new Role();
        role2.setId(1);
        role2.setName("fakeRole2");

        given(roleRepository.findAll()).willReturn(List.of(role1, role2));

        // action
        List<GetRoleDto> result = roleService.getAllRoles();

        // assertions
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(roleRepository, times(1)).findAll();

        // H2 andmebaas
    }

    @Test
    public void getRoleByIdSuccess() {
        // setup
        Role role = new Role();
        role.setId(1);
        role.setName("fakeRole");

        given(roleRepository.findById(1)).willReturn(Optional.of(role));

        // action
        GetRoleDto result = roleService.getRoleById(1);

        // assertions
        assertNotNull(result);
        assertEquals(result.id(), role.getId());
        assertEquals(result.name(), role.getName());
        verify(roleRepository, times(1)).findById(1);
    }

    @Test
    public void getRoleByIdError() {
        // setup
        given(roleRepository.findById(1)).willReturn(Optional.empty());

        // action
         Exception exception = assertThrows(ResponseStatusException.class, () -> roleService.getRoleById(1));

         // assertions
//        System.out.println(exception.toString());
        assertEquals("404 NOT_FOUND", exception.getMessage());
    }
}
