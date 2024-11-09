package com.nt.noctua.repository;

import com.nt.noctua.Model.ModelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<ModelUser, Long> {

    ModelUser findByUsername(String username);
    ModelUser findByEmail(String email);
    Boolean existsByUsernameOrEmail(String username, String email);


}
