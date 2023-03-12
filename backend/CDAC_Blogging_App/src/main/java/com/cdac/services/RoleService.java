package com.cdac.services;

import com.cdac.custom_exceptions.ResourceNotFoundException;
import com.cdac.dtos.RoleDTO;
import com.cdac.entities.Role;
import com.cdac.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ModelMapper modelMapper;

    public RoleDTO insertRole(RoleDTO roleDTO) {
        Role role = dtoToRole(roleDTO);
        role = roleRepo.save(role);
        roleDTO = roleToDTO(role);

        return roleDTO;
    }

    // Read all the users from DB -
    public List<RoleDTO> getAllRoles() {
        return roleRepo.findAll().stream().map((role) -> roleToDTO(role)).collect(Collectors.toList());
    }

    // Find a user by email -
    public RoleDTO getRoleById(long id) {
        Optional<Role> optionalRole = roleRepo.findById(id);
        if (optionalRole.isEmpty()) throw new ResourceNotFoundException("Role not found with the id: " + id);

        return roleToDTO(optionalRole.get());
    }

    // Update the existing user in the DB -
    public RoleDTO updateRole(long id, RoleDTO updatedRoleDTO) {
        getRoleById(id);
        Role role = dtoToRole(updatedRoleDTO);
        role.setId(id);

        role = roleRepo.save(role);

        return roleToDTO(role);
    }

    // Delete the existing user from the DB -
    public RoleDTO deleteROle(long id) {
        RoleDTO roleDTO = getRoleById(id);
        roleRepo.deleteById(id);

        return roleDTO;
    }

    public RoleDTO roleToDTO(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

    public Role dtoToRole(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, Role.class);
    }

}
