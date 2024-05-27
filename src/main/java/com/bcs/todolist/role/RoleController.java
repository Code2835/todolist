package com.bcs.todolist.role;

import com.bcs.todolist.role.dto.GetRoleDto;
import com.bcs.todolist.role.dto.SaveOrUpdateRoleDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<GetRoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public GetRoleDto getRoleById(@PathVariable("id") Integer id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public void saveRole(@Valid @RequestBody SaveOrUpdateRoleDto role) {
        roleService.saveRole(role);
    }

    @PutMapping("/{id}")
    public void updateRole(@PathVariable("id") Integer id, @Valid @RequestBody SaveOrUpdateRoleDto role) {
        roleService.updateRole(id, role);
    }

//    @PostMapping
//    public void saveRole(@RequestBody Role role) {
//        roleService.saveRole(role);
//    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") Integer id) {
        roleService.deleteRole(id);
    }
}
