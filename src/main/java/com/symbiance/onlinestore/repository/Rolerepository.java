package com.symbiance.onlinestore.repository;

import com.symbiance.onlinestore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Rolerepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
