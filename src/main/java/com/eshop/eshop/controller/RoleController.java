package com.eshop.eshop.controller;


import com.eshop.eshop.entity.RoleEntity;
import com.eshop.eshop.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/private/auth/roles")
@AllArgsConstructor
@Tag(name = "Roles Controller", description = "This url for handle roles in  E-Shop.")
public class RoleController {

    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleEntity> createUser(@RequestBody RoleEntity role) {
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoleEntity>> getListOfRoles() {
        return new ResponseEntity<>(roleService.getListOfRoles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleEntity> getRoleById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(roleService.getRoleById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleEntity> updatedRole(@PathVariable(name = "id") Long id, @RequestBody RoleEntity role) {
        return new ResponseEntity<>(roleService.updateRole(id, role), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RoleEntity> deleteRole(@PathVariable(name = "id") Long id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
