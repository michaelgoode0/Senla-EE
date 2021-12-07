package com.test.project.security.dao;

import com.test.project.security.api.repository.RoleRepository;
import com.test.project.security.enums.RoleName;
import com.test.project.security.model.Role;
import com.test.project.security.model.User;
import com.test.project.util.AbstractDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EnumType;

@Repository
public class RoleRepositoryImpl extends AbstractDao<Role> implements RoleRepository {

    @Override
    public Role findRoleByName(RoleName roleName) {
        return  entityManager.createQuery("select r from Role r where r.role=:role", Role.class)
                .setParameter("role",roleName)
                .getSingleResult();
    }
}
