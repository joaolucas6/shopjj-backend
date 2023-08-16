package com.joaolucas.shopjj.repositories;

import com.joaolucas.shopjj.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
