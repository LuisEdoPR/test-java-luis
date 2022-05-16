package com.challenge.test.domain;

import com.challenge.test.infrastructure.database.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
