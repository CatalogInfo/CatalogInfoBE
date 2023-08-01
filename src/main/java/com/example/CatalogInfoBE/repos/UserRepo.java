package com.example.CatalogInfoBE.repos;

import com.example.CatalogInfoBE.models.table_entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "users")
public interface UserRepo extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findByUsernameContaining(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
