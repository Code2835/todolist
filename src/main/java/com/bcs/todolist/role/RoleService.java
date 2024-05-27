package com.bcs.todolist.role;

import com.bcs.todolist.common.FileProcessor;
import com.bcs.todolist.common.FileProcessorService;
import com.bcs.todolist.role.dto.GetRoleDto;
import com.bcs.todolist.role.dto.SaveOrUpdateRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class RoleService {
    private final static String DATA_FILE_NAME = "role.json";
    private FileProcessor fileProcessor;

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

//    @Autowired
//    public RoleService(FileProcessor fileProcessor) {
//        this.fileProcessor = fileProcessor;
//    }

    public List<GetRoleDto> getAllRoles() {
//        return roleRepository.findAll();
        List<Role> roles = roleRepository.findAll();

        return roles.stream()
                .map(role -> new GetRoleDto(role.getId(), role.getName()))
                .toList();
    }

//    public List<Role> getAllRoles() {
//        return this.fileProcessor.readAsList(DATA_FILE_NAME, Role[].class);
//    }

    public GetRoleDto getRoleById(Integer id) {
        Optional<Role> role = roleRepository.findById(id);

        if (role.isPresent()) {
//            return role.get();
            Role roleById = role.get();

            return new GetRoleDto(roleById.getId(), roleById.getName());
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

//    public Role getRoleById(Integer id) {
//        List<Role> roles = getAllRoles();
//
//        for (Role role : roles) {
//            if (role.getId().equals(id)) {
//                return role;
//            }
//        }
//
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//    }

    public void saveRole(SaveOrUpdateRoleDto dto) {
        Role role = new Role();
        role.setName(dto.name());

        try {
            roleRepository.save(role);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

//    public void saveRole(Role role) {
//        roleRepository.save(role);
//    }

    public void updateRole(Integer id, SaveOrUpdateRoleDto dto) {
        if (!roleRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        try {
            roleRepository.update(id, dto.name());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

//    public void saveRole(Role role) {
//        List<Role> roles = getAllRoles();
//
//        roles.add(role);
//
//        fileProcessor.update(DATA_FILE_NAME, roles);
//    }

    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }

//    public void deleteRole(Integer id) {
//        List<Role> roles = getAllRoles();
//
//        for (Role role : roles) {
//            if (role.getId().equals(id)) {
//                roles.remove(role);
//                break;
//            }
//        }
//
//        fileProcessor.update(DATA_FILE_NAME, roles);
//    }
}
