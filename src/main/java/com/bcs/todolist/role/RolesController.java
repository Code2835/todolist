package com.bcs.todolist.role;

import com.bcs.todolist.role.dto.GetRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RolesController {
    private RoleService roleService;

    @Autowired
    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/roles")
    public String getRoles(Model model) {
        List<GetRoleDto> roles = roleService.getAllRoles();

        model.addAttribute("roles", roles);

        return "roles";
    }
}
