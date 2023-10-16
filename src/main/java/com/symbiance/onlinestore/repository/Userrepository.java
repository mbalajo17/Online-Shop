package com.symbiance.onlinestore.repository;

import com.symbiance.onlinestore.model.Prduct;
import com.symbiance.onlinestore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.Path;
import java.util.Map;
import java.util.Optional;

public interface Userrepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String name);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
    Optional<Object> findUserByEmail(String email);


    User findUserById(Long id);

}
