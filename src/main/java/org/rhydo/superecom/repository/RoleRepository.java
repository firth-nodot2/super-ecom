package org.rhydo.superecom.repository;

import org.rhydo.superecom.enu.AppRole;
import org.rhydo.superecom.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
