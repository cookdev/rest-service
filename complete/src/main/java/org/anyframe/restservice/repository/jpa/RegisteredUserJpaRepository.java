package org.anyframe.restservice.repository.jpa;

import org.anyframe.restservice.domain.User;
import org.anyframe.restservice.repository.RegisteredUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUserJpaRepository extends RegisteredUserRepository, JpaRepository<User, String> {
}
